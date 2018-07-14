package com.sununiq.scaffold.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description:
 *
 * @author: sununiq
 *
 * @create: 2018-07-14 12:52
 **/
@Setter
@Getter
public class RestResult<T> {
	private String code;

	private String desc;

	private T data;

	public static <T> RestResult<T> newOkResult(T data) {
		RestResult<T> restResult = new RestResult<>();
		restResult.setCode("0");
		restResult.setData(data);
		return restResult;
	}

	public static <T> RestResult<T> newFailResult(String code, String desc) {
		RestResult<T> restResult = new RestResult<>();
		restResult.setCode(code);
		restResult.setDesc(desc);
		return restResult;
	}
}
