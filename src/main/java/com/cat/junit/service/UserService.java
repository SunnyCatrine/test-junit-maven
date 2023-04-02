package com.cat.junit.service;

import com.cat.junit.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> userList = new ArrayList<>();

    public List<User> getAll() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
