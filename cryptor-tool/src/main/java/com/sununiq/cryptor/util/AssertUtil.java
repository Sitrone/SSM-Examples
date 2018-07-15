package com.sununiq.cryptor.util;

import com.sununiq.cryptor.CryptorException;

import java.io.File;
import java.util.Objects;

/**
 * @program: cryptor-tool
 *
 * @description: 校验工具类
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 09:04
 **/
public abstract class AssertUtil {

	public static void notNull(Object o) {
		if (o == null) {
			throw new CryptorException("Input data is null");
		}
	}

	public static void notBlank(String s) {
		if (s == null || Objects.equals(s.trim(), "")) {
			throw new CryptorException("Input data is blank");
		}
	}

	public static void fileExists(File file) {
		Objects.requireNonNull(file);
		if (!file.isFile() || !file.exists()) {
			throw new CryptorException("Input file is not exists, " + file);
		}
	}

	public static void isPositive(long n) {
		if (n <= 0) {
			throw new CryptorException("Input number is not positive, " + n);
		}
	}

	public static void nonNegative(long n) {
		if (n < 0) {
			throw new CryptorException("Input number is negative, " + n);
		}
	}
}
