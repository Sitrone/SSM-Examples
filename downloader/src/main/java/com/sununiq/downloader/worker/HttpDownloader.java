package com.sununiq.downloader.worker;

import com.sununiq.downloader.domain.DownloadInfo;
import com.sununiq.downloader.domain.DownloadProfile;
import com.sununiq.downloader.domain.DownloaderException;
import com.sununiq.downloader.domain.Slice;
import com.sununiq.downloader.util.ThreadCounter;
import com.sununiq.downloader.util.HttpUtils;
import com.sununiq.downloader.util.UnitUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HttpDownloader {
    @Setter
    @Getter
    private DownloadProfile downloadProfile;

    @Setter
    @Getter
    private URL url;

    @Setter
    @Getter
    private DownloadInfo downloadInfo;

    private String rcdFile;

    private String tempFile;

    private ThreadCounter threadCounter;

    private RandomAccessFile raf;

    private List<DownloaderWorker> workerList = new ArrayList<>();

    // 最后一片可以与前面的片大小不同
    private List<Slice> sliceList = new ArrayList<>();

    public void startDownload() {
        try {
            // 初始化环境信息
            init();

            // 启动多线程进行分片下载
            workerList.forEach(Thread::start);

            // 不断刷新进度
            refreshRcdFile();

            // 下载完毕移动temp文件到正式文件
            Files.move(Paths.get(tempFile), Paths.get(downloadInfo.getAbsFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("Failed to download file :" + url, e);
        }
    }

    /**
     * 定时刷新纪录下载纪录的文件
     */
    private void refreshRcdFile() {
        while (threadCounter.get() > 0) {
            List<Slice> copySliceList = new ArrayList<>();
            sliceList.forEach(it -> copySliceList.add(it.copy()));
            try (RandomAccessFile raf = new RandomAccessFile(rcdFile, "rw")) {
                int threadNum = copySliceList.size();
                raf.writeInt(threadNum);
                for (Slice slice : copySliceList) {
                    raf.writeLong(slice.getStartPos());
                    raf.writeLong(slice.getCurPos());
                    raf.writeLong(slice.getEndPos());
                }
            } catch (IOException e) {
                log.error("Failed to refresh rcdfile.", e);
            }

            HttpUtils.sleep(3 * 1000L);
        }
    }


    /**
     * 初始化下载器
     */
    private void init() throws FileNotFoundException {
        long fileLength = getFileLength();

        tempFile = downloadInfo.getAbsFileName() + ".tmp";
        rcdFile = downloadInfo.getAbsFileName() + ".rcd";

        if(new File(tempFile).exists() && new File(rcdFile).exists()) {
            loadPosition();
        } else {
            int threadNum = getThreadNumber(fileLength);

            threadCounter = new ThreadCounter(threadNum);

            initSliceList(fileLength, threadNum);
        }

        sliceList.forEach(slice -> workerList.add(new DownloaderWorker(slice)));

        raf = new RandomAccessFile(new File(tempFile), "rw");
    }

    /**
     * 加载断点位置数据
     */
    private void loadPosition() {
        try (RandomAccessFile raf = new RandomAccessFile(rcdFile, "r")) {
            int threadNum = raf.readInt();
            for (int i = 0; i < threadNum; i++) {
                Slice slice = new Slice();
                slice.setStartPos(raf.readLong());
                slice.setCurPos(raf.readLong());
                slice.setEndPos(raf.readLong());
                sliceList.add(slice);
            }

            threadCounter = new ThreadCounter(threadNum);
        } catch (IOException e) {
            throw new DownloaderException("Failed to load rcdfile.", e);
        }
    }

    /**
     * 初始化分片信息
     */
    private void initSliceList(long fileLength, int threadNum) {
        int sliceSize = (int) (fileLength / threadNum);
        int startPos = 0;
        while (fileLength > 0) {
            Slice slice = new Slice();
            slice.setStartPos(startPos);
            slice.setEndPos(startPos + sliceSize);
            sliceList.add(slice);

            fileLength -= sliceSize;
            startPos += sliceSize;
        }
    }

    /**
     * 线程数计算策略
     */
    private int getThreadNumber(long fileLength) {
        int threadNum;
        if(fileLength < 20 * UnitUtil.MB) {
            threadNum = 1;
        } else if(fileLength < 200 * UnitUtil.MB) {
            threadNum = 5;
        } else {
            threadNum = downloadProfile.getThreadNumber();
        }
        return threadNum;
    }


    private long getFileLength() {
        HttpURLConnection conn = HttpUtils.openHttpURLConnection(url, downloadProfile);
        return conn.getContentLengthLong();
    }

    @Slf4j
    class DownloaderWorker extends Thread {
        private Slice slice;

        public DownloaderWorker(Slice slice) {
            this.slice = slice;
        }

        @Override
        public void run() {
            super.run();
            setName("download-" + sliceList.indexOf(slice));

            int retryTimes = downloadProfile.getRetryTimes();
            boolean runSuccess = false;

            while (retryTimes > 0) {
                try {
                    download();
                    runSuccess = true;
                    break;
                } catch (Exception e) {
                    retryTimes -= 1;
                    HttpUtils.sleep(downloadProfile.getRetryInterval());
                    log.error("Failed to download " + url + ", retryTimes is: " + (downloadProfile.getRetryTimes() - retryTimes));
                }
            }

            if(runSuccess) {
                threadCounter.decrement();
            } else {
                threadCounter.clear();
                throw new DownloaderException("Failed to download part file " + slice);
            }
        }

        private void download() {
            try {
                HttpURLConnection conn = HttpUtils.openHttpURLConnection(url, downloadProfile, slice);

                check(conn);

                doDownloadSlice(conn);
            } catch (IOException e) {
                throw new DownloaderException("Failed to download part " + slice, e);
            }
        }

        private void doDownloadSlice(HttpURLConnection conn) throws IOException {
            try (InputStream inputStream = conn.getInputStream()) {
                byte[] buffer = new byte[8 * 1024];
                int read = 0;
                while (slice.getRemaining() > 0 && (read = inputStream.read(buffer)) != -1) {
                    raf.write(buffer, (int) slice.getCurPos(), read);
                    slice.setCurPos(slice.getCurPos() + read);
                }
            }
        }

        private void check(HttpURLConnection conn) {
            int partSize = conn.getHeaderFieldInt("Content-Length", -1);
            if(partSize != slice.getLength()) {
                throw new DownloaderException("Get " + slice + " length failed");
            }
        }
    }
}

