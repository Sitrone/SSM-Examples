package com.sununiq.scaffold.service.impl;

import com.sununiq.scaffold.common.aspect.Log;
import com.sununiq.scaffold.dao.UserMapper;
import com.sununiq.scaffold.domain.User;
import com.sununiq.scaffold.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Log(object = "queryById", type = "service")
	@Override
	public User queryById(int id) {
		return userMapper.queryById(id);
	}
}