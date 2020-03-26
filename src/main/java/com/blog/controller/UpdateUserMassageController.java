package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.entity.UserMessage;
import com.blog.service.UserMassageService;
import com.blog.utils.BlogUtils;
import com.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UpdateUserMassageController {

    @Autowired
    private UserMassageService userMassageService;


    @PostMapping("updateUM")
    @ResponseBody
    public Result updateUM(@RequestBody UserMessage userMessage, HttpSession session) {
        if (!BlogUtils.isObjectNull(userMessage)) {
            Long userAccount = (Long) session.getAttribute("userAccount");
            UserMessage defaultUserMassage = userMassageService.getUMByAccount(userAccount);
            userMessage.setUser_name(defaultUserMassage.getUser_name());
            userMessage.setUser_account(defaultUserMassage.getUser_account());
            userMessage.setId(defaultUserMassage.getId());
            if (defaultUserMassage.equals(userMessage)) {
                return new Result(100, "信息无更新！", null);
            } else {
                try {
                    userMassageService.updateUM(userMessage);
                    return new Result(100, "更新成功", null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(200, "数据库操作出现异常", null);
                }
            }
        }
        return new Result(100, "提交空数据", null);

    }
}
