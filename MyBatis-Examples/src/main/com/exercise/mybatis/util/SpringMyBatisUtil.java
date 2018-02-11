package com.exercise.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * 使用MyBatis-Spring来实现自动提交
 */
public abstract class SpringMyBatisUtil {
    private static final SqlSession sqlSession;

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

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
        sqlSession = new SqlSessionTemplate(sqlSessionFactory);
    }

    public static SqlSession getSqlSession() {
        return sqlSession;
    }
}
