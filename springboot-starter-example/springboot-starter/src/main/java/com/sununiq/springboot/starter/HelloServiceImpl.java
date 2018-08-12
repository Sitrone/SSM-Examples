package com.sununiq.springboot.starter;

/**
 * @program: springboot-starter-example
 *
 * @description: implementation
 *
 * @author: sununiq
 *
 * @create: 2018-08-12 19:46
 **/
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHello(String content) {
		System.out.println(String.format("Hello %s.", content));
	}
}
