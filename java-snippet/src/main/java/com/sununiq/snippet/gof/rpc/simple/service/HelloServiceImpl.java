package com.sununiq.snippet.gof.rpc.simple.service;

/**
 * @program: java-snippet
 *
 * @description:
 *
 * @author: sununiq
 *
 * @create: 2018-07-23 21:30
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
