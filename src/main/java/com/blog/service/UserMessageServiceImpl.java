package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.UserMessage;
import com.blog.mapper.UserMassageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageServiceImpl implements UserMassageService {

    @Autowired
    private UserMassageMapper userMassageMapper;


    @Override
    public UserMessage getUMByAccount(Long user_account) {
        return userMassageMapper.getUserMassageByUserAccount(user_account);
    }

    @Override
    public void insertUM(UserMessage userMessage) {
        userMassageMapper.insertUserMassage(userMessage);
    }

    @Override
    public void updateUM(UserMessage userMessage) {
        userMassageMapper.updateUserMessage(userMessage);
    }
}
