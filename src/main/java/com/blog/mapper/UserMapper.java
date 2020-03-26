package com.blog.mapper;/*
 *@author:
 *@time
 */

import com.blog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 注册新用户
     *
     * @param user
     */
    @Insert("insert into user (name,account_id,password,gmt_creat,gmt_modified) values" +
            "(#{name},#{account_id},#{password},#{gmt_creat},#{gmt_modified})")
    public void insertUser(User user);

    /**
     * 根据用户账号和密码确认用户
     *
     * @param account_id
     * @param password
     * @return
     */
    @Select("select * from user where account_id=#{account_id} and password=#{password}")
    public User userLogin(Long account_id, String password);

    /**
     * 根据用户账号获取用户信息
     *
     * @param account_id
     * @return
     */
    @Select("select * from user where account_id=#{account_id}")
    public User getUserByAccountId(Long account_id);

    /**
     * 更新用户名
     *
     * @param name
     * @param account_id
     * @return
     */
    @Update("update user set name=#{name} where account_id=#{account_id}")
    User updateName(String name, Long account_id);

    /**
     * 更新用户密码
     *
     * @param password
     * @param account_id
     * @return
     */
    @Update("update user set password=#{password} where account_id=#{account_id}")
    User updatePassword(String password, Long account_id);

    /**
     * 根据用户名获取用户
     *
     * @param name
     * @return
     */
    @Select("select * from user where name=#{name}")
    User getUserByName(String name);
}
