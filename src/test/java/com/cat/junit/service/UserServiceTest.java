package com.cat.junit.service;

import com.cat.junit.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@Tag("userService")
@Tag("user")
@Tag("fast")
public class UserServiceTest {
    private UserService userService;

    @BeforeAll
    static void setSomePropertiesForAllTests() {
        System.out.println("Before all:");
    }

    @BeforeEach
    void setUserService() {
        System.out.println("Before Each:" + this);
        userService = new UserService();
    }

    @Test
    @Tag("getAll")
    void emptyListIfNoUsers() {
        System.out.println("EmptyListIfNoUsers:" + this);
        List<User> userList = userService.getAll();

        assertThat(userList)
                .as("Have to be empty")
                .isEmpty();
//        assertTrue(userList.isEmpty(), "Have to be empty");
    }

    @Test
    @Tag("getAll")
    void sizeIfUsersAdded() {
        System.out.println("SizeIfUsersAdded:" + this);
        userService.addUser(new User());
        userService.addUser(new User());
        List<User> userList = userService.getAll();
//        int userListSize = userList.size();

        assertThat(userList)
                .as("Have to be 2")
                .hasSize(2);
//        assertEquals(2, userListSize, "Have to be 2");
    }

    @Test
    @Tag("login")
    void throwIllegalArgumentExceptionIfUserNameOrPasswordIsNull() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> userService.login("name", null), "password null"),
                () -> assertThrows(IllegalArgumentException.class, () -> userService.login(null, "password"), "name null")
        );
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
