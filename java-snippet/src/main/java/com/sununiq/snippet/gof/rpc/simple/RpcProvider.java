package com.sununiq.snippet.gof.rpc.simple;

import com.sununiq.snippet.gof.rpc.simple.service.HelloService;
import com.sununiq.snippet.gof.rpc.simple.service.HelloServiceImpl;

/**
 * @program: java-snippet
 *
 * @description: 服务提供方
 *
 * @author: sununiq
 *
 * @create: 2018-07-23 21:31
 **/
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }

}
