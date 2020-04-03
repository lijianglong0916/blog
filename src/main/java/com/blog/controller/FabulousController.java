package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.FabulousDto;
import com.blog.service.FabulousService;
import com.blog.utils.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class FabulousController {
    @Autowired
    private FabulousService fabulousService;

    @PostMapping("commentLike")
    @ResponseBody
    public String commentLike(@RequestBody FabulousDto fabulousDto, HttpSession session) {
        String msg = "";
        //逻辑判空后将点赞信息插入数据库
        if (!BlogUtils.isObjectNull(fabulousDto.getQuestion_id(), session.getAttribute("userAccount"))) {

            boolean isExist = fabulousService.isFabulousExist(fabulousDto.getQuestion_id(), (Long) session.getAttribute("userAccount"));

            if (isExist) {
                msg = "已取消赞!";
            } else {
                msg = "已点赞！";
            }
            fabulousService.insertOrRemoveFabulous(fabulousDto.getQuestion_id(), (Long) session.getAttribute("userAccount"));
        }
        //重定向返回当前页面
        return msg;
    }
}
