package com.sununiq.scaffold.controller;

import com.sununiq.scaffold.domain.User;
import com.sununiq.scaffold.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "一个测试API", notes = "第一个测试API", response = User.class)
	@ApiImplicitParam(name = "id", value = "id类型参数", required = true, paramType = "path", dataType = "int")
	@GetMapping(value = "/v1/user/{id}")
	@ResponseBody
	public User findOneUser(@PathVariable("id") int id) {
		log.info("Request id is:{}.", id);
		return userService.queryById(id);
	}

	@ApiOperation(value = "页面api", hidden = true)
	@GetMapping(value = "/v1/page/{id}")
	public String showUserPage(ModelMap model, @PathVariable int id) {
		log.info("Request id is:{}.", id);
		User user = userService.queryById(id);

		log.info("Find user:{}", user);
		model.addAttribute(user);

		return "User";
	}
}
