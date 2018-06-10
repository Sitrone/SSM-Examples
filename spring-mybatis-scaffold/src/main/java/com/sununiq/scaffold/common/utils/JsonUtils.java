package com.sununiq.scaffold.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sununiq.scaffold.common.exception.ScaffoldException;

import java.io.IOException;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: json工具类
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 19:09
 **/
public abstract class JsonUtils {

	private static final ObjectMapper SER_MAPPER = new ObjectMapper();

	private static final ObjectMapper DE_MAPPER = new ObjectMapper();

	static {

	}

	public static String toJson(Object o) throws ScaffoldException {
		try {
			return SER_MAPPER.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new ScaffoldException("Failed to convert object to json.", e);
		}
	}

	public <T> T toObject(String s, Class<T> clazz) throws ScaffoldException {
		try {
			return DE_MAPPER.readValue(s, clazz);
		} catch (IOException e) {
			throw new ScaffoldException("Failed to convert string to object.", e);
		}
	}
}
