package com.sununiq.scaffold.common.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: ip白名单过滤器
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 20:12
 **/
public class IPFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

	}

	@Override
	public void destroy() {

	}
}
