package com.sununiq.downloader.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 下载器参数类
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class DownloadProfile {
    private int threadNumber;
    private int retryTimes;
    private long retryInterval;
    private long connTimeout;
    private long readTimeout;

    public static DownloadProfile defaultProfile() {
        DownloadProfile downloadProfile = new DownloadProfile();
        downloadProfile.setRetryTimes(10);
        downloadProfile.setRetryInterval(20 * 1000);
        downloadProfile.setThreadNumber(Runtime.getRuntime().availableProcessors());
        downloadProfile.setConnTimeout(20 * 1000);
        downloadProfile.setReadTimeout(40 * 1000);
        return downloadProfile;
    }
}
