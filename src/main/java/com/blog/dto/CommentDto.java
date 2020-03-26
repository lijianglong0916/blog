package com.blog.dto;/*
 *@author:
 *@time
 */

import lombok.Data;

@Data
public class CommentDto {
    private Integer questionId;
    private String commentText;
}
