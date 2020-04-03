package com.blog.entity;/*
 *@author:
 *@time
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Question {
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
}
