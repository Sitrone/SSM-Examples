package com.sununiq.scaffold;

import com.sununiq.scaffold.dao.UserMapper;
import com.sununiq.scaffold.domain.User;
import com.sununiq.scaffold.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: mybatis-test
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 18:13
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyBatisTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IUserService userService;

	@Test
	public void test() {
		User user = userMapper.queryById(1);
		log.info(user.toString());
	}

	@Test
	public void testService() {
		User user = userService.queryById(2);
		log.info(user.toString());
	}
}
