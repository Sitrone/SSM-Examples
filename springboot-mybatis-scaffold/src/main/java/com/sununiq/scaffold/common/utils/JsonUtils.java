package com.sununiq.scaffold.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
		// 序列化忽略null字段
		SER_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		// 反序列化忽略对象中对象中有而json串中没有的字段
		DE_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

	/**
	 * 复杂对象的解析
	 */
	public <T> T toObjectBy(String s, TypeReference<T> typeReference) throws ScaffoldException {
		try {
			return DE_MAPPER.readValue(s, typeReference);
		} catch (IOException e) {
			throw new ScaffoldException("Failed to convert string to object.", e);
		}
	}
}
