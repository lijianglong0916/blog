package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 验证登录用户账户和密码并返回该用户信息
     *
     * @param accountId
     * @param password
     * @return
     */
    @Override
    public User userLogin(Long accountId, String password) {
        return userMapper.userLogin(accountId, password);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param account_id
     * @return
     */
    @Override
    public User getUserByAccountId(Long account_id) {
        return userMapper.getUserByAccountId(account_id);
    }

    /**
     * 更新用户名
     *
     * @param username
     * @param accountId
     */
    @Override
    public void updateUserName(String username, Long accountId) {
        userMapper.updateName(username, accountId);
    }

    /**
     * 更新密码
     *
     * @param accountId
     * @param password
     */
    @Override
    public void updateUserPassword(Long accountId, String password) {
        userMapper.updatePassword(password, accountId);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void userRegister(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

}
