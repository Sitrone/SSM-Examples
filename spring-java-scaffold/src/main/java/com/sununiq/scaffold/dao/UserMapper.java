package com.sununiq.scaffold.dao;

import com.sununiq.scaffold.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	void insertUser(User user);

	User queryById(int id);
}
