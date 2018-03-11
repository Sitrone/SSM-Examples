package com.sununiq.github.v1;

import com.sununiq.github.common.HOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class SessionFactoryUtil {
    private static SessionFactory factory;

    static {
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("v1/hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        factory = cfg.buildSessionFactory();
    }

    public static void doHibernateOperation(HOperation hOperation) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            hOperation.doAction(session);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

}
