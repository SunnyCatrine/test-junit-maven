package com.cat.junit.service;

import com.cat.junit.entity.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    UserService userService;

    @BeforeAll
    static void setSomePropertiesForAllTests() {
        System.out.println("Before all");
    }


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

    @AfterAll
    static void someCleanActionsForAllTests() {
        System.out.println("After all:");
    }
}
