package com.sununiq.downloader.util;

import com.sununiq.downloader.domain.DownloadProfile;
import com.sununiq.downloader.domain.DownloaderException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public abstract class HttpUtils {

    /**
     * 判断服务器是否支持分段请求
     */
    public static boolean isServerSupportRange(URL url, DownloadProfile profile) {
        try {
            log.debug("begin to test if {} support range or not.", url.getPath());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(profile.getConnTimeout());
            urlConnection.setReadTimeout(profile.getReadTimeout());
            urlConnection.setRequestProperty("Range", "bytes=0-0");

            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();

            log.debug("{} support range.", url.getPath());
            return responseCode == 206;
        } catch (IOException e) {
            throw new DownloaderException("Failed to konw if server support range or not.", e);
        }
    }
}
