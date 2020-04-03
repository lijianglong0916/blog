package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.dto.QuestionDto;
import com.blog.entity.Question;
import com.blog.mapper.QuestionMapper;
import com.blog.mapper.UserMapper;
import com.blog.utils.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 更新阅读数
     *
     * @param readCount
     * @param questionId
     */
    @Override
    public void updateQuestionReadCount(int readCount, int questionId) {
        questionMapper.updateQueReadCount(readCount, questionId);
    }

    /**
     * 更新点赞数
     *
     * @param likeCount
     * @param questionId
     */
    @Override
    public void updateQuestionLikeCount(int likeCount, int questionId) {
        questionMapper.updateQueLikeCount(likeCount, questionId);
    }

    /**
     * 通过question的ID查询question
     *
     * @param questionId
     * @return
     */
    @Override
    public Question getQuestionById(int questionId) {
        return questionMapper.getQuestionById(questionId);
    }

    /**
     * 插入question
     *
     * @param question
     */
    @Override
    public void insertQuestion(Question question) {
        questionMapper.insertQue(question);
    }

    /**
     * 根据id删除question
     *
     * @param id
     */
    @Override
    public void deleteQueById(int id) {
        questionMapper.deleteById(id);
    }

    /**
     * 按分页方式查询
     *
     * @param page
     * @param listNumber
     * @return
     */
    @Override
    public List<Question> getQuestion(int page, int listNumber) {
        return questionMapper.questionPageList((page - 1) * listNumber, listNumber);
    }

    /**
     * 查询该用户的所有question
     *
     * @param page
     * @param listNumber
     * @param creatorId
     * @return
     */
    @Override
    public List<Question> getQuestionByUser(int page, int listNumber, Long creatorId) {
        return questionMapper.getQuestionPageByUserAccount(page, listNumber, creatorId);
    }

    @Override
    public List<Question> getQuestionByUser(Long creatorId) {
        return questionMapper.getQuestionByUserAccount(creatorId);
    }

    /**
     * 根据question的id查询问题并转化为可以直接展示的question信息
     *
     * @param questionId
     * @return
     */
    @Override
    public QuestionDto getQuestionByQueId(int questionId) {
        Question question = questionMapper.getQuestionById(questionId);
        if (BlogUtils.isObjectNull(question)) {
            return null;
        } else {
            QuestionDto questionDto = convertQueToDto(question);

            return questionDto;
        }
    }

    @Override
    public ListQuestionDto getQuestionsByMsg(String message, int page, int listNumber) {
        //获取所有用户的提问总数,按分页值查取分页展示的数据
        List<Question> questionList = questionMapper.getQueByMsg(message, page, listNumber);
        int totalCountQuestion = questionList.size();
        ListQuestionDto listQuestion = new ListQuestionDto();
        //设置分页
        listQuestion.setPageList(page, totalCountQuestion, listNumber);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questionList) {
            questionDtos.add(convertQueToDto(question));
        }
        listQuestion.setQuestionDtos(questionDtos);
        return listQuestion;
    }

    /**
     * 根据tag获取相关问题
     *
     * @param tag
     * @return
     */
    @Override
    public Question[] getLikeQueByTag(String tag) {
        return questionMapper.getQueLikeTag(tag, 1, 8);
    }

    @Override
    public Question[] getLikeQues() {
        return questionMapper.getQueLikes(1, 12);
    }

    /**
     * 按分页查询问题并转化为可以直接展示的question信息
     *
     * @param page
     * @param listNumber
     * @return
     */
    @Override
    public ListQuestionDto getListQuestions(int page, int listNumber) {
        //获取所有用户的提问总数
        int totalCount = questionMapper.count();
        //按分页值查取分页展示的数据
        List<Question> questionList = questionMapper.questionPageList((page - 1) * listNumber, listNumber);
        ListQuestionDto listQuestionDto = new ListQuestionDto();
        //设置分页
        listQuestionDto.setPageList(page, totalCount, listNumber);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questionList) {
            questionDtos.add(convertQueToDto(question));
        }
        listQuestionDto.setQuestionDtos(questionDtos);
        return listQuestionDto;
    }


    /**
     * 分页模式查询给用户的所有question
     *
     * @param page
     * @param listNumber
     * @param creatorId
     * @return
     */
    @Override
    public ListQuestionDto getListQuestionsByUserId(int page, int listNumber, Long creatorId) {
        //根据用户账号id查取所有该id下的问题的总数
        int totalCountQuestion = questionMapper.countByUser(creatorId);
        //根据账户id查取分页问题
        List<Question> questionList = questionMapper.getQuestionPageByUserAccount((page - 1) * listNumber, listNumber, creatorId);
        ListQuestionDto listQuestionDto = new ListQuestionDto();
        //设置分页信息
        listQuestionDto.setPageList(page, totalCountQuestion, listNumber);
        //转换为QuestionDto
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questionList) {
            questionDtos.add(convertQueToDto(question));
        }
        //将查出的问题赋值给DTO
        listQuestionDto.setQuestionDtos((questionDtos));
        return listQuestionDto;
    }

    /**
     * 更新用户信息
     *
     * @param question
     */
    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    /**
     * 将question转换为questionDto
     */

    private QuestionDto convertQueToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setRead_count(question.getRead_count());
        questionDto.setTag(question.getTag());
        questionDto.setTitle(question.getTitle());
        questionDto.setComment_count(question.getComment_count());
        questionDto.setCreatorId(question.getCreatorId());
        questionDto.setLike_count(question.getLike_count());
        questionDto.setDescription(question.getDescription());
        questionDto.setGmt_create(question.getGmt_create());
        questionDto.setGmt_modified(question.getGmt_modified());
        questionDto.setCreatorName(userMapper.getUserByAccountId(question.getCreatorId()).getName());
        return questionDto;
    }
}
