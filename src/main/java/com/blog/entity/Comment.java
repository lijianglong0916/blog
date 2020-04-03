package com.blog.entity;/*
 *@author:
 *@time
 */

import lombok.Data;

@Data
public class Comment {
    private int id;
    private String commentText;
    private Long creatorId;
    private int question_id;
    private Long comment_create;
    private int comment_count;
    private int sub_count;
}
