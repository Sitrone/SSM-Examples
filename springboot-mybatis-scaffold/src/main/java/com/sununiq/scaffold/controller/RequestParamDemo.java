package com.sununiq.scaffold.controller;

import com.sununiq.scaffold.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 请求参数绑定的几种方式
 *
 * spring对应请求方法参数的处理、响应返回值的处理对应两个接口分别是：
 * 		HandlerMethodArgumentResolver和HandlerMethodReturnValueHandler
 *
 * @author: sununiq
 *
 * @create: 2018-08-25 21:18
 **/
@Slf4j
@RestController
public class RequestParamDemo {

	/**
	 *  请求url：/ssm/testRequestParam?name=tom&age=18
	 */
	@GetMapping("/testRequestParam")
	public User testRequestParam(@RequestParam String name, @RequestParam Integer age,
			@RequestParam(required = false, defaultValue = "unkonown") String desc) {
		log.info("testRequestParam, name is:{}, age is:{}, desc is:{}", name, age, desc);
		return User.builder().name(name).age(age).build();
	}

	/**
	 * 请求url：/ssm/testRequestParam?name=tom&age=18
	 * 与上面的区别是，无法对请求参数做个性化定制，比如如果没有传值可以设定默认值, 这种方式需要在controller自己去适配
	 * 这种方式的优点在于，多余3个请求参数的时候，会非常有用，提高代码的封装性和可维护性
	 */
	@GetMapping("/testRequestParamObject")
	public User testRequestParamObject(User user) {
		log.info("RequestParamObject test, {}", user);
		return user;
	}

	/**
	 * 请求体，如果不注明请求的content-type,则需要在注解里面指明告诉框架怎么解析
	 * 响应也是同理
	 */
	@PostMapping("/requestBody")
	public User testRequestBody(@RequestBody User user) {
		log.info("RequestBody test, {}", user.toString());
		return user;
	}

	/**
	 * 请求url：/ssm/testDate?2018-08-25
	 * 通过注解的方式指定date的格式
	 */
	@GetMapping("testDate")
	public Date testDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		log.info("Request param self-defined date is:{}", date);
		return date;
	}


	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

	/**
	 * 请求的url：/ssm/testSelfDefined?2018-08-25
	 * 通过指定绑定的解析参数关系来解析
	 */
	@GetMapping("testSelfDefined")
	public Date testDate2(Date date) {
		log.info("Request param self-defined date is:{}", date);
		return date;
	}
}
