package com.exercise.mybatis.v0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JdbcDataSource {

    //数据库用户名
    private static final String USERNAME = "tester";
    //数据库密码
    private static final String PASSWORD = "12345678";
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static final String JDBCURL = "jdbc:mysql://localhost:3306/goo";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
