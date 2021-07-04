package com.controller;

import com.pojo.Result;
import com.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getUsername")
    public Result getUsername() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Result result = null;
        if (user != null) {
            result = new Result(true, "查询用户名成功", user.getUsername());
        } else {
            result = new Result(false, "查询用户名失败");
        }
        return result;
    }
}
