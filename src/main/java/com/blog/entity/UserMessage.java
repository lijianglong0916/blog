package com.blog.entity;/*
 *@author:
 *@time
 */

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class UserMessage {
    private int id;
    private Long user_account;
    private String user_name;
    private String user_true_name;
    private String sex;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String position;
    private String company;
    private String education;
    private String school;
    private String industry;
    private String introduction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMessage)) return false;
        UserMessage that = (UserMessage) o;
        return id == that.id &&
                Objects.equals(user_account, that.user_account) &&
                Objects.equals(user_name, that.user_name) &&
                Objects.equals(user_true_name, that.user_true_name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(position, that.position) &&
                Objects.equals(company, that.company) &&
                Objects.equals(education, that.education) &&
                Objects.equals(school, that.school) &&
                Objects.equals(industry, that.industry) &&
                Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_account, user_name, user_true_name, sex, birthday, position, company, education, school, industry, introduction);
    }
}
