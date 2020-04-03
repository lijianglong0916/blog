package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("test")
    public Result getUser(@RequestParam String userName) {
        redisTemplate.opsForSet().add(userName, userName);
        return new Result(100, "userName", userName);
    }
}
