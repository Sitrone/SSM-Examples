package io.sununiq.conf.impl;

import io.sununiq.conf.Conf;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class PropertyConf extends BaseConf {

    public Conf load(String conf) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(conf)) {
            super.properties.load(inputStream);
            return this;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
