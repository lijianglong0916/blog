package com.blog.mapper;/*
 *@author:
 *@time
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RootMapper {
    /**
     * 判断当前用户是否是root用户
     *
     * @return
     */
    @Select("select user_account from root_user where user_account=#{user_account}")
    public Long isRootUser(Long user_account);
}
