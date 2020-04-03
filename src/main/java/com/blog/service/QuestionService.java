package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.dto.QuestionDto;
import com.blog.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    /**
     * 根据message获取相关问题
     */
    ListQuestionDto getQuestionsByMsg(String message, int page, int listNumber);

    /**
     * 根据tag获取相关问题
     *
     * @param tag
     * @return
     */
    Question[] getLikeQueByTag(String tag);

    /**
     * 获取热点问题
     *
     * @return
     */
    Question[] getLikeQues();


    /**
     * 按分页获取所有问题
     */
    ListQuestionDto getListQuestions(int page, int listNumber);

    /**
     * 根据用户的账号按分页获取问题
     */

    ListQuestionDto getListQuestionsByUserId(int page, int listNumber, Long userAccount);

    /**
     * 插入问题
     */
    void insertQuestion(Question questionDto);

    /**
     * 根据id删除question
     *
     * @param id
     */
    void deleteQueById(int id);

    List<Question> getQuestion(int page, int listNumber);

    List<Question> getQuestionByUser(int page, int listNumber, Long userAccount);
    
    List<Question> getQuestionByUser(Long creatorId);

    QuestionDto getQuestionByQueId(int questionId);

    void updateQuestion(Question question);

    Question getQuestionById(int questionId);

    /**
     * 更新阅读数
     *
     * @param readCount
     * @param questionId
     */
    void updateQuestionReadCount(int readCount, int questionId);

    /**
     * 更新点赞数
     *
     * @param likeCount
     * @param questionId
     */
    void updateQuestionLikeCount(int likeCount, int questionId);
}
