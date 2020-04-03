package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.entity.HeadPicture;
import com.blog.entity.User;
import com.blog.entity.UserMessage;
import com.blog.service.HeadPictureService;
import com.blog.service.UserMassageService;
import com.blog.service.UserService;
import com.blog.utils.BlogUtils;
import com.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private HeadPictureService headPictureService;

    @Autowired
    private UserMassageService userMassageService;

    @Value("${default-head-picture}")
    private String defaultHeadPicture;

    /**
     * 用户账号注册
     * 并保证事务
     *
     * @param user
     * @return
     */
    @Transactional
    @PostMapping("userRegister")
    @ResponseBody
    public Result register(@RequestBody User user) {
        if (!BlogUtils.isObjectNull(user.getAccount_id(), user.getName(), user.getPassword())) {
            //账号是否被注册
            User userByAccountId = userService.getUserByAccountId(user.getAccount_id());
            //用户昵称是否被使用
            User userByName = userService.getUserByName(user.getName());
            if (userByAccountId != null) {
                return new Result(100, "注册失败，账户已注册", null);
            } else if (userByName != null) {
                return new Result(100, "注册失败，昵称已使用", null);
            } else {
                user.setGmt_creat(System.currentTimeMillis());
                HeadPicture headPicture = new HeadPicture();
                UserMessage userMessage = new UserMessage();
                userMessage.setUser_account(user.getAccount_id());
                userMessage.setUser_name(user.getName());
                headPicture.setPicture_url(defaultHeadPicture);
                headPicture.setUser_account(user.getAccount_id());
                //注册完成后同时完成用户默认头像和默认个人信息的设置
                try {
                    userService.userRegister(user);
                    //初始化默认头像信息
                    headPictureService.insertHp(headPicture);
                    //初始化默认个人资料信息
                    userMassageService.insertUM(userMessage);
                    return new Result(100, "注册成功，请返回主页登录", null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(100, "数据库操作异常", null);
                }
            }


        }
        return new Result(200, "注册失败，内容为空", null);
    }
}
