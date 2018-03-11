package com.sununiq.github.v2;

import com.sununiq.github.common.User;
import org.hibernate.query.Query;

public class HibernateTest {
    public static void main(String[] args) {
        doQueryByIdHql();
    }

    /**
     * 使用hql语法
     */
    private static void doQueryByIdHql() {
        Query<User> query = SessionFactoryUtil.getSession()
                .createQuery("select u from User as u where id = :myId", User.class);
        query.setParameter("myId", 1);
        System.out.println(query.uniqueResult());
    }
}
