package com.exercise.mybatis.v1;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    User queryById(int id);
}
