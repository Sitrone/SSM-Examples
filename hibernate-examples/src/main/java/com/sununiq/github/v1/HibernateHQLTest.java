package com.sununiq.github.v1;

import com.sununiq.github.common.User;
import org.hibernate.query.Query;

import java.util.List;

/**
 * 项目集成hibernate，仅仅使用hibernate，使用xml映射文件
 * <p>
 * 使用HQL语言：https://docs.jboss.org/hibernate/orm/3.5/reference/zh-CN/html/queryhql.html
 */
public class HibernateHQLTest {

    public static void main(String[] args) {

    }


    /**
     * 直接操作session
     * 通过ID获取
     */
    private static void doQueryById() {
        SessionFactoryUtil.doHibernateOperation((session -> {
            User user = session.get(User.class, 1);
            System.out.println(user);
        }));
    }

    /**
     * 获取所有
     */
    private static void doQueryAllAction() {
        SessionFactoryUtil.doHibernateOperation((session -> {
            List<?> from_user = session.createQuery("From User").list();
            System.out.println(from_user);
        }));
    }

    /**
     * 使用hql语法
     */
    private static void doQueryByIdHql() {
        SessionFactoryUtil.doHibernateOperation((session -> {
            Query<User> query = session.createQuery("select u from User as u where id = :myId", User.class);
            query.setParameter("myId", 1);
            System.out.println(query.uniqueResult());
        }));
    }
}
