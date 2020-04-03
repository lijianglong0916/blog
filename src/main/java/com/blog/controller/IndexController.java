package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.entity.Question;
import com.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    //cookie实现前台登录与数据库交互
    public String index(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "listNumber", defaultValue = "8") int listNumber) {

        ListQuestionDto listQuestionDto = questionService.getListQuestions(page, listNumber);
        Question[] likeQues = questionService.getLikeQues();
        List<Question> hotQuestions = new ArrayList<>();
        for (Question likeQue : likeQues) {
            hotQuestions.add(likeQue);
        }
        model.addAttribute("hotQuestions", hotQuestions);
        model.addAttribute("listQuestionDto", listQuestionDto);
        return "index";
    }
}
