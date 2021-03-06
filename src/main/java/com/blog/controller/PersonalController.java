package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.entity.User;
import com.blog.entity.UserMessage;
import com.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PersonalController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private FabulousService fabulousService;

    @Autowired
    private HeadPictureService headPictureService;

    @Autowired
    private UserMassageService userMassageService;

    @Autowired
    private RootUserService rootUserService;

    @GetMapping("myQuestion")
    public ModelAndView myQuestion(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "listNumber", defaultValue = "8") int listNumber,
            HttpSession session) {

        ModelAndView view = new ModelAndView();
        String titleName = "我的提问";
        Long userAccount = (Long) session.getAttribute("userAccount");
        User user = userService.getUserByAccountId(userAccount);
        ListQuestionDto userQuestionDto = questionService.getListQuestionsByUserId(page, listNumber, user.getAccount_id());
        view.addObject("userQuestionDto", userQuestionDto);
        view.addObject("titleName", titleName);
        view.setViewName("personalDetail");
        return view;
    }

    @GetMapping("myLike")
    public ModelAndView myLike(@RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "listNumber", defaultValue = "8") int listNumber,
                               HttpSession session) {
        ModelAndView view = new ModelAndView();
        String titleName = "我的点赞";
        ListQuestionDto userQuestionDto = fabulousService.getQuestions(page, listNumber, (Long) session.getAttribute("userAccount"));
        view.addObject("userQuestionDto", userQuestionDto);
        view.addObject("titleName", titleName);
        view.setViewName("personalDetail");
        return view;
    }

    @GetMapping("personal")
    public ModelAndView personal(@RequestParam(name = "creatorName", defaultValue = "") String creatorName, HttpSession session) {
        ModelAndView view = new ModelAndView();
        Long userAccount = 0L;
        //当前被访问的用户是否是当前用户
        boolean isCurrentPerson = false;

        //当前用户是否是root用户
        boolean isRootUser = false;
        //当前是否有用户登录
        if (session.getAttribute("userAccount") != null) {
            userAccount = (Long) session.getAttribute("userAccount");
            //如果当前访问用户是当前登录用户
            if (creatorName.equals("") || userAccount.equals(userService.getUserByName(creatorName).getAccount_id())) {
                isCurrentPerson = true;
                isRootUser = rootUserService.isRootUser(userAccount);
            } else {
                isCurrentPerson = false;
                userAccount = userService.getUserByName(creatorName).getAccount_id();
            }

        } else {
            isCurrentPerson = false;
            userAccount = userService.getUserByName(creatorName).getAccount_id();
        }

        String headP = headPictureService.getHPByUserAccount(userAccount).getPicture_url();
        UserMessage userMessage = userMassageService.getUMByAccount(userAccount);
        view.addObject("isCurrentPerson", isCurrentPerson);
        view.addObject("headP", headP);
        view.addObject("userMessage", userMessage);
        view.addObject("isRootUser", isRootUser);
        view.setViewName("personal");
        return view;
    }
}
