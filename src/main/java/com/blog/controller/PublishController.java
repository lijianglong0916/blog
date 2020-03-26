package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.QuestionDto;
import com.blog.entity.Question;
import com.blog.service.QuestionService;
import com.blog.service.UserService;
import com.blog.utils.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;


    @GetMapping("publish")
    public String publish(Model model) {
        QuestionDto questionDto = new QuestionDto();
        model.addAttribute("questionDto", questionDto);
        model.addAttribute("pub", "提交");
        return "publish";
    }

    @PostMapping("publish")
    @ResponseBody
    public String doPublish(@RequestBody QuestionDto questionDto,
                            HttpServletRequest request) {
        Long userAccount = (Long) request.getSession().getAttribute("userAccount");
        if (userAccount == null) {
            request.getSession().setAttribute("logState", "用户未登录，请先登录！");
        } else {
            Question question = questionService.getQuestionById(questionDto.getId());
            if (BlogUtils.isObjectNull(question, questionDto.getId())) {
                question = new Question();
                question.setTitle(questionDto.getTitle());
                question.setTag(questionDto.getTag());
                question.setGmt_create(new Date());
                question.setDescription(questionDto.getDescription());
                question.setCreatorId(userAccount);
                question.setGmt_create(new Date());
                question.setGmt_modified(question.getGmt_create());
                questionService.insertQuestion(question);
            } else {
                question.setTitle(questionDto.getTitle());
                question.setDescription(questionDto.getDescription());
                question.setTag(questionDto.getTag());
                questionService.updateQuestion(question);
            }
            request.getSession().setAttribute("logState", null);
        }
        return "success";
    }
}
