package com.sununiq.scaffold.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: 拦截器用法
 *
 * @author: sununiq
 *
 * @create: 2018-07-14 11:29
 **/
@Slf4j
public class DefaultInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("DefaultInterceptor, preHandle ...");
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("DefaultInterceptor, postHandle ...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("DefaultInterceptor, afterCompletingHandle ...");
	}

	/**
	 * 如果被并发执行，将会执行这个方法
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("DefaultInterceptor, afterConcurrentHandlingHandle ...");
	}
}
