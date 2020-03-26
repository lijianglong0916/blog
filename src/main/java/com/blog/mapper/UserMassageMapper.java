package com.blog.mapper;/*
 *@author:
 *@time
 */

import com.blog.entity.UserMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMassageMapper {
    /**
     * 根据user账户获取用户资料信息
     *
     * @param user_account
     * @return
     */
    @Select("select *from user_message where user_account=#{user_account}")
    UserMessage getUserMassageByUserAccount(Long user_account);

    /**
     * 保存用户资料信息
     *
     * @param userMessage
     */
    @Insert("insert into user_message (id,user_account,user_name,user_true_name,sex,birthday,position,company,education,school,industry,introduction) " +
            "values(#{id},#{user_account},#{user_name},#{user_true_name},#{sex},#{birthday},#{position},#{company},#{education},#{school},#{industry},#{introduction})")
    void insertUserMassage(UserMessage userMessage);

    /**
     * 更新用户资料信息
     *
     * @param userMessage
     */
    @Update("update user_message set user_true_name=#{user_true_name},sex=#{sex},birthday=#{birthday},position=#{position}," +
            "company=#{company},education=#{education},school=#{school},industry=#{industry}," +
            "introduction=#{introduction} where user_account=#{user_account}")
    void updateUserMessage(UserMessage userMessage);

}
