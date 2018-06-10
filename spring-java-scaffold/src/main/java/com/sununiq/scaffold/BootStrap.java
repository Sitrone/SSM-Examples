package com.sununiq.scaffold;

import com.sununiq.scaffold.common.Log4j2Utils;
import com.sununiq.scaffold.domain.User;
import com.sununiq.scaffold.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring-java-scaffold
 *
 * @description:
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 21:18
 **/
public class BootStrap {

	public static void main(String[] args) {
		// 初始化log4j2配置文件
		Log4j2Utils.init();

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

		IUserService userService = applicationContext.getBean(IUserService.class);

		User user = userService.queryById(2);
		Logger logger = LoggerFactory.getLogger(BootStrap.class);
		logger.info(user.toString());
	}
}
