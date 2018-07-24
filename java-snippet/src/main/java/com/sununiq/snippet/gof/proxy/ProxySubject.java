package com.sununiq.snippet.gof.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxySubject implements Subject {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxySubject.class);

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String sayHello(String content) {
        try {
            LOGGER.info("Before");
            subject.sayHello(content);
            LOGGER.info("After returning");
            return "Hello " + content;
        } catch (Exception e) {
            LOGGER.info("Ex {}", e.getMessage());
            throw e;
        } finally {
            LOGGER.info("After");
        }
    }

    @Override
    public String request() {
        return "Static real request";
    }


}
