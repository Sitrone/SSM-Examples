package com.exercise.mybatis.v1;

import com.exercise.mybatis.util.SpringMyBatisUtil;

import java.lang.reflect.Method;

/**
 * MyBatis-Spring jar通过jdk代理管理sqlSession的生命周期
 * @see org.mybatis.spring.SqlSessionTemplate.SqlSessionInterceptor#invoke(Object, Method, Object[])
 */
public class TestMyBatisWithSpring {

    public static void main(String[] args) {
        testGetByMapper();
        testGet();
    }

    private static void testGet() {
        User user = SpringMyBatisUtil.getSqlSession().selectOne("queryById", 1);
        System.out.println(user);
    }

    private static void testGetByMapper() {
        UserMapper mapper = SpringMyBatisUtil.getSqlSession().getMapper(UserMapper.class);
        User user = mapper.queryById(1);
        System.out.println(user);
    }
}
