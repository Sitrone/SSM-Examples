package com.sununiq.downloader.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 下载器参数类
 */
@Getter
@Setter
@ToString
public class DownloadProfile {
    private int threadNumber;
    private int retryTimes;
    private int connTimeout;
    private int readTimeout;

    public static DownloadProfile defaultProfile() {
        DownloadProfile downloadProfile = new DownloadProfile();
        downloadProfile.setRetryTimes(10);
        downloadProfile.setThreadNumber(Runtime.getRuntime().availableProcessors());
        downloadProfile.setConnTimeout(20 * 1000);
        downloadProfile.setReadTimeout(40 * 1000);
        return downloadProfile;
    }
}
