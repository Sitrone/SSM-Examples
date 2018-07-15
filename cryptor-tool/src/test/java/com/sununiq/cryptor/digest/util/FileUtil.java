package com.sununiq.cryptor.digest.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program: cryptor-tool
 *
 * @description: 测试文件工具类
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 10:15
 **/
public abstract class FileUtil {

	public static Path getFileFromClassPath(String fileName) {
		URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
		try {
			return Paths.get(resource.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
