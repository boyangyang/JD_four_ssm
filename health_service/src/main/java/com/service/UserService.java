package com.service;

import com.pojo.User;

public interface UserService {
    User findUserByname(String username);
}
