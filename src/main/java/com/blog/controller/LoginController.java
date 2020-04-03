package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.entity.Question;
import com.blog.entity.User;
import com.blog.service.QuestionService;
import com.blog.service.UserService;
import com.blog.utils.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("login")
    public String login(Long accountId, String password, Model model,
                        HttpSession session,
                        HttpServletResponse response) {
        if (!BlogUtils.isObjectNull(accountId, password)) {
            User user = userService.userLogin(accountId, password);
            if (user != null) {
                Map<String, Object> msg = new HashMap<>();
                String userName = user.getName();
                session.setAttribute("userName", userName);
                session.setAttribute("userAccount", accountId);
                //移除未登录的状态拦截提示
                session.removeAttribute("logState");
                //token可存放于redis数据库
                String toke = String.valueOf(UUID.randomUUID());
                Cookie cookie = new Cookie("token", toke);
                response.addCookie(cookie);
                ListQuestionDto listQuestionDto = questionService.getListQuestions(1, 8);
                //获取热门问题
                Question[] likeQues = questionService.getLikeQues();
                List<Question> hotQuestions = new ArrayList<>();
                for (Question likeQue : likeQues) {
                    hotQuestions.add(likeQue);
                }
                model.addAttribute("hotQuestions", hotQuestions);
                model.addAttribute("listQuestionDto", listQuestionDto);
                model.addAttribute("userName", userName);
                //登录成功
                return "index";
            }
        }
        //用户账号或密码为空
        return "index";

    }
}
