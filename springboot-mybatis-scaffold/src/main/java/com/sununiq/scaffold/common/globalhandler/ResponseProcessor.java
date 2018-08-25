package com.sununiq.scaffold.common.globalhandler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 统一的响应处理器, 对有标注ResponseBody的注解的响应最后统一封装处理,
 *   多个ResponseBodyAdvice，使用@Order指定顺序
 *
 * @author: sununiq
 *
 * @create: 2018-08-25 22:21
 **/
public class ResponseProcessor implements ResponseBodyAdvice {

	/**
	 * 需要拦截的类型
	 * @param returnType
	 * @param converterType
	 * @return
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return false;
	}

	/**
	 * 重写得到的响应
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		return null;
	}
}
