package com.sununiq.downloader;

import com.sununiq.downloader.domain.DownloadProfile;
import com.sununiq.downloader.util.ConfigHelper;
import com.sununiq.downloader.worker.HttpDownloader;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

/**
 * 启动入口
 */
@Slf4j
public class BootStrap {
    public static void main(String[] args) {

        if(args == null || args.length != 1) {
            log.warn("No url need to download.");
            return;
        }

        try {
            URL url = new URL(args[0]);
            DownloadProfile downloadProfile = initDownloadProfile();

            HttpDownloader downloader = new HttpDownloader();
            downloader.setUrl(url);
            downloader.setDownloadProfile(downloadProfile);

            downloader.startDownload();
        } catch (Exception e) {
            log.error("Failed to downloader url:" + args[0], e);
        }
    }

    /**
     * 初始化下载配置
     */
    private static DownloadProfile initDownloadProfile() {
        long connTimeout = ConfigHelper.getLong("downloader.connTimeout", 20 * 1000L);
        long readTimeout = ConfigHelper.getLong("downloader.readTimeout", 20 * 1000L);
        int retryTimes = ConfigHelper.getInt("downloader.retry.times", 10);
        long retryInterval = ConfigHelper.getLong("downloader.retry.interval", 20 * 1000L);
        int threadNum = ConfigHelper.getInt("downloader.thread.number", 10);

        DownloadProfile downloadProfile = new DownloadProfile();
        downloadProfile.setRetryTimes(retryTimes);
        downloadProfile.setThreadNumber(threadNum);
        downloadProfile.setConnTimeout(connTimeout);
        downloadProfile.setReadTimeout(readTimeout);
        downloadProfile.setRetryInterval(retryInterval);
        return downloadProfile;
    }
}
