package com.sununiq.cryptor;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: cryptor-tool
 *
 * @description: 加解密异常类
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 08:48
 **/
public class CryptorException extends RuntimeException{
	@Getter
	@Setter
	private String message;

	public CryptorException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public CryptorException(Throwable cause) {
		super(cause);
	}

	public CryptorException(String message) {
		this.message = message;
	}
}
