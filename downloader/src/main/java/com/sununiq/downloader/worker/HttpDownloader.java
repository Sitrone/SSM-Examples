package com.sununiq.downloader.worker;

import com.sununiq.downloader.domain.DownloadProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class HttpDownloader implements IDownloader {
    private DownloadProfile downloadProfile;


    @Override
    public void startDownload() {

    }
}
