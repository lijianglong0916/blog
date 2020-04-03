package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.dto.UserDto;
import com.blog.entity.Comment;
import com.blog.entity.Question;
import com.blog.entity.User;
import com.blog.service.*;
import com.blog.utils.BlogUtils;
import com.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService ;

    @Autowired
    private FabulousService fabulousService;

    @Autowired
    private HeadPictureService headPictureService ;

    @GetMapping("manage")
    public String manage(Model model,
                         @RequestParam(name = "page", defaultValue = "1") int page,
                         @RequestParam(name = "listNumber", defaultValue = "8") int listNumber) {
        ListQuestionDto listQuestionDto = questionService.getListQuestions(page, listNumber);
        model.addAttribute("listQuestionDto", listQuestionDto);
        model.addAttribute("userDto", null);
        return "manage";
    }


    @RequestMapping("questionManage")
    public ModelAndView questionManage(@RequestParam(name = "page", defaultValue = "1") int page,
                                       @RequestParam(name = "listNumber", defaultValue = "8") int listNumber) {
        ModelAndView view = new ModelAndView("manage");
        ListQuestionDto listQuestionDto = questionService.getListQuestions(page, listNumber);
        view.addObject("listQuestionDto", listQuestionDto);
        UserDto userDto = userService.getUsers(page, listNumber);
        view.addObject("userDto", null);
        return view;

    }

    @RequestMapping("userManage")
    public ModelAndView userManage(@RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "listNumber", defaultValue = "8") int listNumber) {
        ModelAndView view = new ModelAndView("manage");
        UserDto userDto = userService.getUsers(page, listNumber);
        view.addObject("userDto", userDto);
        view.addObject("listQuestionDto", null);
        return view;
    }

    @Transactional
    @RequestMapping("deleteUser")
    @ResponseBody
    public Result deleteUser(@RequestBody User user) {
        if (!BlogUtils.isObjectNull(user.getAccount_id())) {
            List<Question> questionByUser = questionService.getQuestionByUser(user.getAccount_id());
            for (Question question : questionByUser) {
                try {
                    List<Comment> comments = commentService.selectCommentByQuestionId(question.getId());
                    for (Comment comment : comments) {
                        commentService.deleteComment(comment.getId());
                    }
                    fabulousService.removeFabulous(question.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(100,"数据库操作失败",null);
                }
            }
            try {
                headPictureService.deleteHP(user.getAccount_id());
                userService.deleteUser(user.getAccount_id());
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(100,"删除用户数据库操作失败",null);
            }
        }
        return new Result(100,"删除成功",null);
    }

}
