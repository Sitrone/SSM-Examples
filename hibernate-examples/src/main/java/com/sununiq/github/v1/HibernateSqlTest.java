package com.sununiq.github.v1;

import com.sununiq.github.common.User;
import org.hibernate.query.NativeQuery;

/**
 * 项目集成hibernate，仅仅使用hibernate，使用xml映射文件
 * <p>
 * 使用原生sql语句查询
 */
public class HibernateSqlTest {

    public static void main(String[] args) {
        doQueryAllAction();
        doQueryByIdSql();
    }


    /**
     * 获取所有
     */
    private static void doQueryAllAction() {
        String sql = "select * from user";
        SessionFactoryUtil.doHibernateOperation((session -> {
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            System.out.println(query.list());
        }));
    }

    /**
     * 使用sql的方式查找对应列
     */
    private static void doQueryByIdSql() {
//        String sql = "select * from user where id = ?";
        String sql = "select * from user where id = :myID";
        SessionFactoryUtil.doHibernateOperation((session -> {
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
//            query.setParameter(0, 1);
            query.setParameter("myID", 1);
            System.out.println(query.uniqueResult());
        }));
    }
}
