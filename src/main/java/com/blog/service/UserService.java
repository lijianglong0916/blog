package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.dto.UserDto;
import com.blog.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface UserService {

    /**
     * 验证登录用户账户和密码并返回该用户信息
     *
     * @param accountId
     * @param password
     * @return
     */
    User userLogin(Long accountId, String password);

    /**
     * 获取所有用户信息并分页，除root用户
     * @param page
     * @param listNumber
     * @return
     */
    UserDto getUsers(int page, int listNumber);

    /**
     * 根据用户账号获取用户
     *
     * @param account_id
     * @return
     */
    User getUserByAccountId(Long account_id);

    /**
     * x修改用户名
     *
     * @param username
     * @param accountId
     */
    void updateUserName(String username, Long accountId);

    /**
     * 修改用户密码
     *
     * @param accountId
     * @param password
     */
    void updateUserPassword(Long accountId, String password);

    /**
     * 注册用户
     *
     * @param user
     */
    void userRegister(User user);

    /**
     * 根据用户名获取用户
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 根据账户删除user
     * @param account_id
     */
    void deleteUser(Long account_id);
}
