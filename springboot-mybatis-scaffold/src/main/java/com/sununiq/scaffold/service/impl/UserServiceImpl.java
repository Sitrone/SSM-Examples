package com.sununiq.scaffold.service.impl;

import com.sununiq.scaffold.dao.UserMapper;
import com.sununiq.scaffold.domain.User;
import com.sununiq.scaffold.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryById(int id) {
		return userMapper.queryById(id);
	}

	@Override
	public void createUser(User user) {
		userMapper.insertUser(user);
	}
}