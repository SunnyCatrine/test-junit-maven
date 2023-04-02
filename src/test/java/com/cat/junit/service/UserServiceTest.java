package com.cat.junit.service;

import com.cat.junit.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    UserService userService;


    @BeforeEach
    void setUserService() {
        System.out.println("Before Each:" + this);
        userService = new UserService();

    }

    @Test
    void emptyListIfNoUsers() {
        System.out.println("EmptyListIfNoUsers:" + this);
        List<User> userList = userService.getAll();
        assertTrue(userList.isEmpty(), "Have to be empty");
    }

    @Test
    void sizeIfUsersAdded() {
        System.out.println("SizeIfUsersAdded:" + this);
        userService.addUser(new User());
        userService.addUser(new User());
        List<User> userList = userService.getAll();
        int userListSize = userList.size();
        assertEquals(2, userListSize, "Have to be 2");
    }

    @AfterEach
    void someCleanActions() {
        System.out.println("AfterEach:" + this);
    }
}
