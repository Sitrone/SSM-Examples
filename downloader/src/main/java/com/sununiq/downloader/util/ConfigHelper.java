package com.sununiq.downloader.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public abstract class ConfigHelper {
    private static final Properties properties = new Properties();

    static {
        try {
            try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config.properties")) {

                properties.load(inputStream);
            }
        } catch (IOException e) {
            log.error("Failed to load config.properties file.", e);
        }
    }

    public static long getLong(String keyName, long defaultValue) {
        return (long) properties.getOrDefault(keyName, defaultValue);
    }

    public static String getString(String keyName, String defaultValue) {
        return (String) properties.getOrDefault(keyName, defaultValue);
    }

    public static int getInt(String keyName, int defaultValue) {
        return (int) properties.getOrDefault(keyName, defaultValue);
    }
}
