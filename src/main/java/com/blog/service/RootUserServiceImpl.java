package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.mapper.RootMapper;
import com.blog.utils.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootUserServiceImpl implements RootUserService {

    @Autowired
    private RootMapper rootMapper;

    /**
     * 当前用户是否是root用户，是，返回true
     *
     * @param user_account
     * @return
     */
    @Override
    public boolean isRootUser(Long user_account) {
        if (!BlogUtils.isObjectNull(rootMapper.isRootUser(user_account))) {
            return true;
        }
        return false;
    }
}
