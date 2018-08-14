package com.sununiq.app.service;

import com.sununiq.springboot.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springboot-starter-example-parent
 *
 * @description: hello 服务
 *
 * @author: sununiq
 *
 * @create: 2018-08-12 21:26
 **/
@Service
public class DefaultHelloService {

	@Autowired
	private HelloService helloService;

	public void doService(String content) {
		helloService.sayHello(content);
	}
}
