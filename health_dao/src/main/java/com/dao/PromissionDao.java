package com.dao;


import com.pojo.Permission;

import java.util.Set;

public interface PromissionDao {
    Set<Permission> findPermissionByRoleId(int roleId);
}
