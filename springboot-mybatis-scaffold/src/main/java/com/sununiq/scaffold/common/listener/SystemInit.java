package com.sununiq.scaffold.common.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 系统启动配置
 *
 * @author: sununiq
 *
 * @create: 2018-08-04 09:23
 **/
@Slf4j
public class SystemInit implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("System started ...");


		log.info("System started success.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("system destroyed.");
	}
}
