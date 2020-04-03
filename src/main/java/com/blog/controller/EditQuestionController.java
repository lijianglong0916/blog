package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.QuestionDto;
import com.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditQuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam(name = "questionId") Integer questionId) {
        ModelAndView view = new ModelAndView();
        if (questionId != null) {
            QuestionDto questionDto = questionService.getQuestionByQueId(questionId);
            view.addObject("questionDto", questionDto);
            view.addObject("pub", "确认修改");
        }

        view.setViewName("publish");
        return view;
    }
}
