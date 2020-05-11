package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.dto.ListQuestionDto;
import com.blog.dto.QuestionDto;
import com.blog.entity.Fabulous;
import com.blog.entity.Question;
import com.blog.mapper.FabulousMapper;
import com.blog.mapper.QuestionMapper;
import com.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabulousServiceImpl implements FabulousService {

    @Autowired
    private FabulousMapper fabulousMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户是否对指定文章点过赞，true为点过赞
     *
     * @param question_id
     * @param user_id
     * @return
     */
    @Override
    public boolean isFabulousExist(int question_id, Long user_id) {
        Fabulous fabulous = fabulousMapper.isExistFabulous(user_id, question_id);
        if (null != fabulous) {
            return true;
        }
        return false;
    }

    /**
     * 获取question赞数量
     *
     * @param question_id
     * @return
     */
    @Override
    public int fabulousNumber(int question_id) {
        int number = questionMapper.getLikeCount(question_id);
        return number;
    }

    /**
     * 获取用户点赞过的所以文章
     *
     * @param user_id
     * @return
     */
    @Override
    public ListQuestionDto getQuestions(int page, int listNumber, Long user_id) {
        int totalCountQuestion = fabulousMapper.count(user_id);
        Fabulous[] fabulous = fabulousMapper.selectFabulous((page - 1) * listNumber, listNumber, user_id);
        List<Question> questionList = new ArrayList<>();
        for (Fabulous fabulous1 : fabulous) {
            questionList.add(questionMapper.getQuestionById(fabulous1.getQuestion_id()));
        }
        ListQuestionDto listQuestionDto = new ListQuestionDto();
        //设置分页
        listQuestionDto.setPageList(page, totalCountQuestion, listNumber);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questionList) {
            questionDtos.add(convertQueToDto(question));
        }
        listQuestionDto.setQuestionDtos(questionDtos);
        return listQuestionDto;
    }

    /**
     * 移除该question的所有赞
     *
     * @param question_id
     */
    @Override
    public void removeFabulous(int question_id) {
        List<Fabulous> fabulous = fabulousMapper.getFabulousByQue(question_id);
        if (fabulous != null) {
            for (Fabulous fabulous1 : fabulous) {
                fabulousMapper.removeFabulous(fabulous1.getId());
            }
        }

    }

    /**
     * 点赞信息更新，有则删除，无则添加
     *
     * @param question_id
     * @param user_id
     */
    @Override
    public void insertOrRemoveFabulous(int question_id, Long user_id) {
        Fabulous fabulous = fabulousMapper.isExistFabulous(user_id, question_id);
        if (fabulous == null) {

            fabulous = new Fabulous();
            fabulous.setExist_state(true);
            fabulous.setQuestion_id(question_id);
            fabulous.setUser_id(user_id);
            fabulousMapper.insertFabulous(fabulous);
            questionMapper.updateQueLikeCount(questionMapper.getLikeCount(question_id) + 1, question_id);

        } else {
            fabulousMapper.removeFabulous(fabulous.getId());
            questionMapper.updateQueLikeCount(questionMapper.getLikeCount(question_id) - 1, question_id);
        }
    }

    /**
     * 将question转换为questionDto
     *
     * @param question
     * @return
     */
    private QuestionDto convertQueToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setRead_count(question.getRead_count());
        questionDto.setTag(question.getTag());
        questionDto.setTitle(question.getTitle());
        questionDto.setComment_count(question.getComment_count());
        questionDto.setCreatorId(question.getCreatorId());
        questionDto.setDescription(question.getDescription());
        questionDto.setGmt_create(question.getGmt_create());
        questionDto.setGmt_modified(question.getGmt_modified());
        questionDto.setCreatorName(userMapper.getUserByAccountId(question.getCreatorId()).getName());
        return questionDto;
    }
}
