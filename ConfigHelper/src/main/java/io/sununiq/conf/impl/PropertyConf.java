package io.sununiq.conf.impl;

import io.sununiq.conf.Conf;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertyConf extends BaseConf {

    public Conf load(String conf) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(conf)) {
            Properties temp = new Properties();
            temp.load(inputStream);
            super.properties = temp;
            return this;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
