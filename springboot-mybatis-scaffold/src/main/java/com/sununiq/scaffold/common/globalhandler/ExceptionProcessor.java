package com.sununiq.scaffold.common.globalhandler;

import com.sununiq.scaffold.domain.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 全局异常处理类，避免堆栈打印到前端
 *
 * @author: sununiq
 *
 * @create: 2018-08-25 22:11
 **/
@ControllerAdvice
public class ExceptionProcessor {

	@ExceptionHandler(Throwable.class)
	public Response<Object> handleException(final Throwable t) {
		return Response.builder().result("1").desc(t.getMessage()).build();
	}
}