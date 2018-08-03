package com.sununiq.scaffold.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: web mvc 配置
 *
 * @author: sununiq
 *
 * @create: 2018-08-03 22:13
 **/
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	/**
	 * 增加对swagger首页的映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

		super.addResourceHandlers(registry);
	}
}
