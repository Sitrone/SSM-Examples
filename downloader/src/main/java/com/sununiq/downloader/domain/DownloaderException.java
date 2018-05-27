package com.sununiq.downloader.domain;

public class DownloaderException extends RuntimeException {
    public DownloaderException(String message) {
        super(message);
    }

    public DownloaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
