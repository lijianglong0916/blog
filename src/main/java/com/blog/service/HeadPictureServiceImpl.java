package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.HeadPicture;
import com.blog.mapper.HeadPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadPictureServiceImpl implements HeadPictureService {
    @Autowired
    private HeadPictureMapper headPictureMapper;

    @Override
    public HeadPicture getHPByUserAccount(Long user_account) {
        return headPictureMapper.getHeadPicByUserAccount(user_account);
    }

    @Override
    public void insertHp(HeadPicture headPicture) {
        headPictureMapper.insertHeadPicture(headPicture);
    }

    @Override
    public void updateHP(String picture_url, Long user_account) {
        headPictureMapper.updateHeadPicture(picture_url, user_account);
    }
}
