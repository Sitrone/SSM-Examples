package com.sununiq.webservice.v1.server;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
    }
}
