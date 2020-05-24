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
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DestroyLogin {
    @Autowired
    private QuestionService questionService;

    /**
     * 退出登录
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("destroy")
    public String destroy(HttpSession session, Model model) {
        session.removeAttribute("userName");
        session.removeAttribute("userAccount");
        ListQuestionDto listQuestionDto = questionService.getListQuestions(1, 8);
        Question[] likeQues = questionService.getLikeQues();
        List<Question> hotQuestions = new ArrayList<>();
        for (Question likeQue : likeQues) {
            hotQuestions.add(likeQue);
        }
        model.addAttribute("hotQuestions", hotQuestions);
        model.addAttribute("listQuestionDto", listQuestionDto);
        return "redirect:/";
    }
}
