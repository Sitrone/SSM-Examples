package com.sununiq.downloader.domain;

import lombok.Data;

/**
 * 下载信息
 */
@Data
public class DownloadInfo {
    private String filePath;
    private String fileName;

    /**
     * 获得全路径名
     */
    public String getAbsFileName() {
        return filePath + fileName;
    }
}
