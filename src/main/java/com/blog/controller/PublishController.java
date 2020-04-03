package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.QuestionDto;
import com.blog.entity.Question;
import com.blog.service.QuestionService;
import com.blog.service.UserService;
import com.blog.utils.BlogUtils;
import com.blog.utils.Result;
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
    public Result doPublish(@RequestBody QuestionDto questionDto,
                            HttpServletRequest request) {
        Long userAccount = (Long) request.getSession().getAttribute("userAccount");
        if (userAccount == null) { //未登录
            request.getSession().setAttribute("logState", "用户未登录，请先登录！");
        } else {//已登录
            request.getSession().setAttribute("logState", null);
            Question question = questionService.getQuestionById(questionDto.getId());
            if (BlogUtils.isObjectNull(question, questionDto.getId())) {
                question = new Question();
                question.setCreatorId(userAccount);
                question.setTitle(questionDto.getTitle());
                question.setTag(questionDto.getTag());
                question.setGmt_create(new Date());
                question.setDescription(questionDto.getDescription());
                question.setGmt_modified(question.getGmt_create());
                try {
                    questionService.insertQuestion(question);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(100, "数据库插入操作失败", null);

                }
            } else {
                question.setTitle(questionDto.getTitle());
                question.setDescription(questionDto.getDescription());
                question.setTag(questionDto.getTag());
                try {
                    questionService.updateQuestion(question);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(100, "数据库更新操作失败", null);
                }

            }
        }
        return new Result(100, "发布成功", null);
    }
}
