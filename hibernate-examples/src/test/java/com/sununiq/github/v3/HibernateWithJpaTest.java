package com.sununiq.github.v3;

import com.sununiq.github.common.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:v3/applicationContext.xml")
public class HibernateWithJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void testEntityManagerQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user1 = entityManager.find(User.class, 1);
        System.out.println(user1);
    }

    @Test
    public void testJpaQuery() {
        User user = userRepository.findOne(1);
        System.out.println(user);
    }
}
