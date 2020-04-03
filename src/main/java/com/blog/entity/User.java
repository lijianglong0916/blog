package com.blog.entity;/*
 *@author:
 *@time
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private int id;
    private Long account_id;
    private String name;
    private String password;
    private Long gmt_creat;
    private Long gmt_modified;
}
