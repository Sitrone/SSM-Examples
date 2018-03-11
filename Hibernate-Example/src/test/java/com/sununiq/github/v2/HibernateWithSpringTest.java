package com.sununiq.github.v2;

import com.sununiq.github.common.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * hibernate 整合spring
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:v2/applicationContext.xml")
public class HibernateWithSpringTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Test
    public void testQueryById() {
        // session 生命周期交给spring管理，专注业务逻辑，无需关注session生命周期
        Query<User> query = sessionFactory.openSession()
                .createQuery("select u from User as u where id = :myId", User.class);
        query.setParameter("myId", 1);
        System.out.println(query.uniqueResult());

        // 关闭session的方式雷同v1中的静态代理模式，业务逻辑封装在HibernateCallback接口的实现类中
        List<?> objects = hibernateTemplate.find("select u from User as u where id = ?", 1);
        System.out.println(objects);
    }
}
