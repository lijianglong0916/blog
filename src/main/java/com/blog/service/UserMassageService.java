package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.UserMessage;
import org.springframework.stereotype.Service;

@Service
public interface UserMassageService {

    /**
     * 根据用户account获取用户资料信息
     *
     * @param user_account
     * @return
     */
    UserMessage getUMByAccount(Long user_account);

    /**
     * 保存用户资料信息
     *
     * @param userMessage
     */
    void insertUM(UserMessage userMessage);

    /**
     * 更新用户资料信息
     *
     * @param userMessage
     */
    void updateUM(UserMessage userMessage);

}
