package com.exercise.mybatis.v1;

import com.exercise.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TestMybatis {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    public static void main(String[] args) {
        testGetBySqlSession();
    }

    private static void testGetByMapper() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.queryById(1);
            System.out.println(user);
        }
    }

    private static void testGetBySqlSession() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            User user = sqlSession.selectOne("queryById", 1);
            System.out.println(user);
        }
    }
}
