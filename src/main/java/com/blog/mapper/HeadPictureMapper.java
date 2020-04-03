package com.blog.mapper;/*
 *@author:
 *@time
 */

import com.blog.entity.HeadPicture;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HeadPictureMapper {
    /**
     * 根据user账户获取头像地址
     *
     * @param user_account
     * @return
     */
    @Select("select *from headPicture where user_account=#{user_account}")
    HeadPicture getHeadPicByUserAccount(Long user_account);

    /**
     * 保存用户头像信息
     *
     * @param headPicture
     */
    @Insert("insert into headPicture (user_account,picture_url) values(#{user_account},#{picture_url})")
    void insertHeadPicture(HeadPicture headPicture);

    /**
     * 更新用户头像信息
     *
     * @param picture_url
     * @param user_account
     */
    @Update("update headPicture set picture_url=#{picture_url} where user_account=#{user_account}")
    void updateHeadPicture(String picture_url, Long user_account);

    /**
     * 根据账号删除头像
     * @param account_id
     */
    @Delete("delete from headPicture where user_account=#{account_id}")
    void deleteHp(Long account_id);
}
