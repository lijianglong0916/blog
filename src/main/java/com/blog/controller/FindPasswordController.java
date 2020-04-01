package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.utils.BlogUtils;
import com.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindPasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("changePass")
    @ResponseBody
    public Result changePass(@RequestBody User user) {
        if (!BlogUtils.isObjectNull(user)) {
            User userByAccountId = userService.getUserByAccountId(user.getAccount_id());
            if (user.getName().equals(userByAccountId.getName())) {
                return new Result(100, userByAccountId.getPassword(), null);
            }
        }
        return new Result(100, "信息不符，无法找回", null);
    }
}
