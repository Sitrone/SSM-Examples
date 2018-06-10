package com.sununiq.scaffold.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: 通用的业务response
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 20:07
 **/
@Setter
@Getter
public class Response<T> {
	// 业务的响应码
	private String code;

	// 业务相应描述
	private String desc;

	// 响应数据
	private T data;
}
