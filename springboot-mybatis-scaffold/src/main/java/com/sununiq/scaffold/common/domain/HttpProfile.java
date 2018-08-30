package com.sununiq.scaffold.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: http请求参数设置
 *
 * @author: sununiq
 *
 * @create: 2018-08-30 22:16
 **/
@Setter
@Getter
public class HttpProfile {
	private int maxReadTimeout = 20_000;
	private int maxConnTimeout = 30_000;
	private int maxPerRoute = 50;
	private int maxTotal = 100;

	public static HttpProfile newDefaultHttpProfile() {
		return new HttpProfile();
	}
}
