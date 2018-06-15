package com.sununiq.scaffold.dao;

import com.sununiq.scaffold.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	void insertUser(User user);

	User queryById(int id);
}
