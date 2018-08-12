package com.sununiq.springboot.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-starter-example
 *
 * @description: 自动装配服务配置到IOC容器中
 *
 * @author: sununiq
 *
 * @create: 2018-08-12 19:47
 **/
@Configuration
@ConditionalOnBean(HelloService.class)
public class HelloServiceAutoConfiguation {

	@Bean
	@ConditionalOnMissingBean
	public HelloService helloService() {
		return new HelloServiceImpl();
	}
}
