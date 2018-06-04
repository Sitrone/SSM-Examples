package com.sununiq.downloader.util;

import com.sununiq.downloader.domain.DownloadProfile;
import com.sununiq.downloader.domain.DownloaderException;
import com.sununiq.downloader.domain.Slice;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class HttpUtils {

    public static void sleep(long timeInMills) {
        try {
            TimeUnit.SECONDS.sleep(timeInMills);
        } catch (InterruptedException ignore) {
            log.warn("{} thread is interrupted.", Thread.currentThread());
        }
    }

    /**
     * 判断服务器是否支持分段请求
     */
    public static boolean isServerSupportRange(URL url, DownloadProfile profile) {
        try {
            log.debug("begin to test if {} support range or not.", url.getPath());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout((int) profile.getConnTimeout());
            urlConnection.setReadTimeout((int) profile.getReadTimeout());
            urlConnection.setRequestProperty("Range", "bytes=0-0");

            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();

            log.debug("{} support range.", url.getPath());
            return responseCode == 206;
        } catch (IOException e) {
            throw new DownloaderException("Failed to konw if server support range or not.", e);
        }
    }

    public static HttpURLConnection openHttpURLConnection(URL url, DownloadProfile downloadProfile) {
        return openHttpURLConnection(url, downloadProfile, null);
    }

    public static HttpURLConnection openHttpURLConnection(URL url, DownloadProfile downloadProfile, Slice slice) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(slice != null) {
                conn.setRequestProperty("Range", String.format("bytes=%d-%d", slice.getStartPos(), slice.getEndPos()));
            }
            conn.setConnectTimeout((int) downloadProfile.getConnTimeout());
            conn.setReadTimeout((int) downloadProfile.getReadTimeout());
            conn.connect();
            return conn;
        } catch (IOException e) {
            throw new DownloaderException("Failed to get connection from:" + url);
        }

    }
}
