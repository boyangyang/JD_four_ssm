package com.dao;

import com.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findRoleByUid(int uid);
}
