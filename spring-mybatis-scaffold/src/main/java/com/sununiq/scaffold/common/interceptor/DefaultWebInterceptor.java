package com.sununiq.scaffold.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: spring mvc 拦截器的实现时基于Java反射，是aop的一种应用，常见用途日志打印，csrf校验，权限校验等
 *
 * @author: sununiq
 *
 * @create: 2018-07-14 11:35
 **/
@Slf4j
public class DefaultWebInterceptor implements WebRequestInterceptor {

	/**
	 * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
	 */
	@Override
	public void preHandle(WebRequest request) throws Exception {
		log.info("WebRequestInterceptor...............................");

		//这个是放到request范围内的，所以只能在当前请求中的request中获取到
		request.setAttribute("request", "request", WebRequest.SCOPE_REQUEST);

		//这个是放到session范围内的，如果环境允许的话它只能在局部的隔离的会话中访问，否则就是在普通的当前会话中可以访问
		request.setAttribute("session", "session", WebRequest.SCOPE_SESSION);

		//如果环境允许的话，它能在全局共享的会话中访问，否则就是在普通的当前会话中访问
		request.setAttribute("globalSession", "globalSession", WebRequest.SCOPE_GLOBAL_SESSION);
	}

	/**
	 * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象，所以可以在
	 */
	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {

	}

	/**
	 * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
	 */
	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {

	}
}
