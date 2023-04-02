package com.cat.junit.service;

import com.cat.junit.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    @Test
    void emptyListIfNoUsers() {
        UserService userService = new UserService();
        List<User> userList = userService.getAll();
        assertTrue(userList.isEmpty(), "Have to be empty");
    }
}
