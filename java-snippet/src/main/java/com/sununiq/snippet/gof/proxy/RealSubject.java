package com.sununiq.snippet.gof.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealSubject implements Subject {
    private static final Logger LOGGER = LoggerFactory.getLogger(RealSubject.class);

    @Override
    public String sayHello(String content) {
        LOGGER.info("Hello {}", content);
        return "Hello " + content;
    }

    @Override
    public String request() {
        return "Real request";
    }
}
