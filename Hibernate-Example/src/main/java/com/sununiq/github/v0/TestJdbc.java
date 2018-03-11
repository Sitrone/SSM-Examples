package com.sununiq.github.v0;


import com.sununiq.github.common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 第零级：原始jdbc操作
 */
public class TestJdbc {
    private static final String GET_USER = "select * from user where id= ?";

    private static final String INSERT_USER = "insert into user(age, name, desc) values(?,?, ?)";

    public static void main(String[] args) {
        User user = getUser();
        System.out.println(user);
    }

    private static User getUser() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = JdbcDataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER)) {
                preparedStatement.setObject(1, 1);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt(1));
                        user.setAge(resultSet.getInt(2));
                        user.setName(resultSet.getString(3));
                        user.setDesc(resultSet.getString(4));
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList.get(0);
    }
}
