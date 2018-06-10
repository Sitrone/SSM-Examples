package com.sununiq.scaffold.controller;

import com.sununiq.scaffold.domain.User;
import com.sununiq.scaffold.service.IUserService;
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

	@GetMapping(value = "/v1/user/{id}")
	@ResponseBody
	public User findOneUser(@PathVariable("id") int id) {
		log.info("Request id is:{}.", id);
		return userService.queryById(id);
	}

	@GetMapping(value = "/v1/page/{id}")
	public String showUserPage(ModelMap model, @PathVariable int id) {
		log.info("Request id is:{}.", id);
		User user = userService.queryById(id);

		log.info("Find user:{}", user);
		model.addAttribute(user);

		return "User";
	}
}
