package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import org.springframework.stereotype.Service;

@Service
public interface FabulousService {
    /**
     * 判断当前用户是否点赞过该文章,true为点过赞
     *
     * @param question_id
     * @param user_id
     * @return
     */
    public boolean isFabulousExist(int question_id, Long user_id);

    /**
     * 查看当前文章下的总赞数
     *
     * @param question_id
     * @return
     */
    public int fabulousNumber(int question_id);

    /**
     * 获取当前用户点赞过的所有文章并分页b
     *
     * @param user_id
     * @return
     */
    public ListQuestionDto getQuestions(int page, int listNumber, Long user_id);

    /**
     * 移除赞
     *
     * @param question_id
     */
    public void removeFabulous(int question_id);

    /**
     * 点赞或取消点赞
     *
     * @param question_id
     * @param user_id
     */
    public void insertOrRemoveFabulous(int question_id, Long user_id);


}