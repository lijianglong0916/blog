package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.dto.UserDto;
import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 获取所有用户信息，不包括root用户
     * @param page
     * @param listNumber
     * @return
     */
    @Override
    public UserDto getUsers(int page, int listNumber) {
        UserDto userDto=new UserDto();
        List<User> allUser = userMapper.getAllUser((page-1)*listNumber,listNumber);
        int totalNumber=allUser.size();
        userDto.setUsers(allUser);
        userDto.setPageList(page, totalNumber, listNumber);
        return userDto;
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

    /**
     * 根据name获取user
     * @param name
     * @return
     */
    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    /**
     * 根据账户删除user
     * @param account_id
     */
    @Override
    public void deleteUser(Long account_id) {
        userMapper.deleteUser(account_id);
    }

}
