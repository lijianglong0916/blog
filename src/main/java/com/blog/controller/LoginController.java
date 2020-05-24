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
import com.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @ResponseBody
    public Result login(@RequestBody User user,
                        HttpSession session,
                        HttpServletResponse response,
                        Model model) {
        ModelAndView view = new ModelAndView("index");
        if (!BlogUtils.isObjectNull(user.getAccount_id(), user.getPassword())) {
            Long accountId = user.getAccount_id();
            String password = user.getPassword();
            User currentUser = userService.userLogin(accountId, password);
            if (currentUser != null) {
                Map<String, Object> msg = new HashMap<>();
                String userName = currentUser.getName();
                Long account_id = currentUser.getAccount_id();
                session.setAttribute("userName", userName);
                session.setAttribute("userAccount", account_id);
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
                view.addObject("hotQuestions", hotQuestions);
                view.addObject("listQuestionDto", listQuestionDto);
                view.addObject("userName", userName);
                //登录成功
                return new Result(200, "登陆成功", null);
            }
            return new Result(200, "登陆失败，密码不正确", null);
        }
        //用户账号或密码为空
        return new Result(200, "账户密码不能为空", null);

    }
}
