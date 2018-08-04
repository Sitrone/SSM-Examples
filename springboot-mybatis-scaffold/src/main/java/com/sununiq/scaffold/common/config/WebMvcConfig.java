package com.sununiq.scaffold.common.config;

import com.sununiq.scaffold.common.filter.IPFilter;
import com.sununiq.scaffold.common.listener.SystemInit;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 增加对swagger首页的映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		super.addResourceHandlers(registry);
	}

	@Bean
	@Order(2)
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(ipFilter());
		registration.addUrlPatterns("/v1/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("ipFilter");
		return registration;
	}

	/**
	 * 实现characterEncode的两种方式，一种是在配置文件中制定，一种是配置filter，优先使用配置文件指定
	 * @return
	 */
	//	@Bean
	//	public FilterRegistrationBean filterRegistrationBean() {
	//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	//		characterEncodingFilter.setForceEncoding(true);
	//		characterEncodingFilter.setEncoding("UTF-8");
	//		registrationBean.setFilter(characterEncodingFilter);
	//		return registrationBean;
	//	}

	/**
	 * 查看bean的javadoc，bean的使用时xml的Java化，default是singleton</br>
	 * <ref https://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch02s02.html></ref>
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<SystemInit> servletListenerRegistrationBean() {
		ServletListenerRegistrationBean<SystemInit> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(systemInitListener());
		return servletListenerRegistrationBean;
	}

	@Bean
	public SystemInit systemInitListener() {
		return new SystemInit();
	}

	@Bean
	public IPFilter ipFilter() {
		return new IPFilter();
	}
}
