package com.sununiq.scaffold.common;

import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @program: spring-java-scaffold
 *
 * @description: log4j2启动工具类
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 21:27
 **/
public abstract class Log4j2Utils {


	public static void init() {
		ClassPathResource resource = new ClassPathResource("log4j2.xml");
		try {
			Configurator.initialize(null, resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
