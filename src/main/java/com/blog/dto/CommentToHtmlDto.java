package com.blog.dto;/*
 *@author:
 *@time
 */

import com.blog.entity.Comment;
import com.blog.entity.User;
import lombok.Data;

@Data
public class CommentToHtmlDto {
    private Comment comment;
    private User user;
}
