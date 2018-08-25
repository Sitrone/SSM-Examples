package com.sununiq.scaffold.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 统一封装响应
 *
 * @author: sununiq
 *
 * @create: 2018-08-25 22:15
 **/
@Data
@Builder
public class Response<T> {
	private String result;
	private String desc;
	private T data;
}
