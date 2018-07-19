package com.sununiq.scaffold.controller;

import com.sununiq.scaffold.common.utils.JsonUtils;
import com.sununiq.scaffold.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources/")
@ContextConfiguration(locations = {"classpath:application.xml", "file:src/main/webapp/spring/spring-mvc.xml"})
public class UserControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private IUserService userService;

	@Autowired
	private MockHttpSession session;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockHttpServletRequest request;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * 测试rest接口
	 * @throws Exception
	 */
	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(get("/v1/user/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(JsonUtils.toJson(this.userService.queryById(1))));
	}

	/**
	 * 测试view层的controller
	 * @throws Exception
	 */
	@Test
	public void showUserPage() throws Exception {
		mockMvc.perform(get("/v1/page/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("User"))
				// 注意模型对象必须实现equals和hashcode'方法，这样才能比较两个对象是否一致
				.andExpect(model().attribute("user", this.userService.queryById(1)));
	}
}