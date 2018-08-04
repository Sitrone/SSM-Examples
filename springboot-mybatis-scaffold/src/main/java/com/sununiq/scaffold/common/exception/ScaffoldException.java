package com.sununiq.scaffold.common.exception;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: 业务异常类
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 19:15
 **/
public class ScaffoldException extends Exception {

	public ScaffoldException(Throwable e) {
		super(e);
	}

	public ScaffoldException(String s, Throwable e) {
		super(s, e);
	}
}
