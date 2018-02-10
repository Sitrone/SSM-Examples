package com.exercise.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Properties;

public class MybatisUtil {
    private final static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis/v1/mybatis-config.xml";
        String propertiesFile = "mybatis/v1/db.properties";
        Reader reader = null;
        Properties properties = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            properties = Resources.getResourceAsProperties(propertiesFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new UncheckedIOException(e);
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
