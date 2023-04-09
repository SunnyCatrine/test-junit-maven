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
        Optional<User> result;
        if (name == null || password == null) {
//            throw new IllegalArgumentException("Login or Password is null");
            result = Optional.empty();
        } else result = Optional.of(User.builder()
                .name(name)
                .password(password)
                .build());
        return result;
    }
}
