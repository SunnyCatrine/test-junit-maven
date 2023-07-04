package com.cat.junit.dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserDao {
    private static UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public boolean delete(Integer intId) {
        boolean result = false;
        try (Connection connection = DriverManager.getConnection("url", "user", "password");
             PreparedStatement preparedStatement = connection.prepareStatement("sql", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, intId);

            int changedRows = preparedStatement.executeUpdate();
            if (changedRows > 0) {
                result = true;
            }
            return result;
        }
    }
}
