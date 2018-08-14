package com.sununiq.app;

import com.sununiq.springboot.starter.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
	@Autowired
	private HelloService helloService;

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void testAutoConfig() {
		helloService.sayHello("from hello starter.");
	}
}
