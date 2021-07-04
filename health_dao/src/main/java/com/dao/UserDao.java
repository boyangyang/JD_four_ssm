package com.dao;

import com.pojo.User;

public interface UserDao {

    User findUserByname(String username);
}
