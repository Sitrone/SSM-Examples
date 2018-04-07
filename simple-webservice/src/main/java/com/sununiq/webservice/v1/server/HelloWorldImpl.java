package com.sununiq.webservice.v1.server;

import javax.jws.WebService;

@WebService(endpointInterface = "com.sununiq.webservice.v1.server.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}
