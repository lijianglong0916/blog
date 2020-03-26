package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.CommentDto;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    @ResponseBody
    public String comment(@RequestBody CommentDto commentDto, HttpSession session) {
        if (commentDto != null) {
            int questionId = commentDto.getQuestionId();
            Comment comment = new Comment();
            comment.setCommentText(commentDto.getCommentText());
            comment.setComment_create(System.currentTimeMillis());
            comment.setQuestion_id(questionId);
            comment.setCreatorId((Long) session.getAttribute("userAccount"));
            commentService.insertIntoComment(comment);
        }
        return "success";
    }


}
