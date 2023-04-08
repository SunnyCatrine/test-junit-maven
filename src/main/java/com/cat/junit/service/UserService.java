package com.cat.junit.service;

import com.cat.junit.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    List<User> userList = new ArrayList<>();

    public List<User> getAll() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public Optional<User> login(String name, String password) {
        if (name == null || password == null) {
            throw new IllegalArgumentException("Login or Password is null");
        }
        return Optional.empty();
    }
}
