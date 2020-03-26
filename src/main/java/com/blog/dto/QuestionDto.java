package com.blog.dto;/*
 *@author:
 *@time
 */

import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto {
    private int id;
    private String title;
    private String description;
    private Date gmt_create;
    private Date gmt_modified;
    private Long creatorId;
    private int comment_count;
    private int read_count;
    private int like_count;
    private String tag;
    private String creatorName;
}
