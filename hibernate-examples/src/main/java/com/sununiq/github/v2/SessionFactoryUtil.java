package com.sununiq.github.v2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.IOException;

public abstract class SessionFactoryUtil {
    //数据库用户名
    private static final String USERNAME = "tester";
    //数据库密码
    private static final String PASSWORD = "12345678";
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static final String JDBCURL = "jdbc:mysql://localhost:3306/goo";

    private static final SessionFactory factory;

    static {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(JDBCURL);
        dataSource.setDriverClassName(DRIVER);

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(new ClassPathResource("v2/hibernate.cfg.xml"));
        try {
            // 注： LocalSessionFactoryBean 将sessionFactory的初始化放在afterProperties方法中，必须手工调用以下
            sessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        factory = sessionFactoryBean.getObject();
    }


    public static Session getSession() {
        return factory.openSession();
    }
}
