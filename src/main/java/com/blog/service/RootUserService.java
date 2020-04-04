package com.blog.service;/*
 *@author:
 *@time
 */

import org.springframework.stereotype.Service;

@Service
public interface RootUserService {

    /**
     * 当前用户是否是root用户，是，返回true
     *
     * @param user_account
     * @return
     */
    boolean isRootUser(Long user_account);
}
