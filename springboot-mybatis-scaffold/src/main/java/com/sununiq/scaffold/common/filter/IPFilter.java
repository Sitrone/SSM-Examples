package com.sununiq.scaffold.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: ip白名单过滤器, 默认的filter会过滤所有的url，如果需要针对特定的url，需要进行配置
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 20:12
 **/
@Slf4j
public class IPFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("start init ipfilter ...");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		log.info("begin ipfilter. ");
		filterChain.doFilter(servletRequest, servletResponse);
		log.info("end ipfilter.");
	}

	@Override
	public void destroy() {
		log.info("start to destroy ipfilter ...");
	}
}
