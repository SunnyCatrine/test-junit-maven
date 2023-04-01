package com.cat.junit.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    @Test
    void usersYokIfUsersNotAdded() {
        Boolean usersYok = new UserService().usersYok();
        assertTrue(usersYok, "Have to be false");
    }
}
