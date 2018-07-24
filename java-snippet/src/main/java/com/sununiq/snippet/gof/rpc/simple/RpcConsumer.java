package com.sununiq.snippet.gof.rpc.simple;

import com.sununiq.snippet.gof.rpc.simple.service.HelloService;

/**
 * @program: java-snippet
 *
 * @description: 服务消费方
 *
 * @author: sununiq
 *
 * @create: 2018-07-23 21:32
 **/
public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
