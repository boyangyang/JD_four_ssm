package com.service.impl;

import com.pojo.Permission;
import com.pojo.Role;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Autowired
    private UserService us;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User userInSQL = us.findUserByname(s);

        if (userInSQL == null) {
            return null;
        }

        List<GrantedAuthority> list = new ArrayList<>();

        Set<Role> roles = userInSQL.getRoles();

        for (Role role : roles) {

            Set<Permission> permissions = role.getPermissions();

            for (Permission permission : permissions) {

                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        return new org.springframework.security.core.userdetails.User(userInSQL.getUsername(), userInSQL.getPassword(), list);
    }
}
