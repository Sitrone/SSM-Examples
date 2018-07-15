package com.sununiq.cryptor.util;

import java.security.SecureRandom;

/**
 * @program: cryptor-tool
 *
 * @description:
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 11:20
 **/
public abstract class RandomUtil {

	public static byte[] newByteArray(int size) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] bytes = new byte[size];
		secureRandom.nextBytes(bytes);
		return bytes;
	}
}
