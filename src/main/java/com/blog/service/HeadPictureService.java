package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.HeadPicture;
import org.springframework.stereotype.Service;

@Service
public interface HeadPictureService {

    /**
     * 根据用户账号获取头像信息head picture
     *
     * @param user_account
     * @return
     */
    HeadPicture getHPByUserAccount(Long user_account);

    /**
     * 插入用户头像信息（head picture）
     *
     * @param headPicture
     */
    void insertHp(HeadPicture headPicture);

    /**
     * 更新用户头像（head picture）
     *
     * @param picture_url
     * @param user_account
     */
    void updateHP(String picture_url, Long user_account);

}
