package com.blog.mapper;/*
 *@author:
 *@time
 */

import com.blog.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    /**
     * 根据tag获取相关问题
     *
     * @param tag
     * @return
     */
    @Select("select * from question where tag like '%${tag}%' order by gmt_create desc limit  #{page},#{listNumber} ")
    public Question[] getQueLikeTag(String tag, int page, int listNumber);

    /**
     * 根据message获取相关问题
     *
     * @param message
     * @return
     */
    @Select("select * from question where title like '%${message}%' or description like '%${message}%' order by gmt_create desc limit  #{page},#{listNumber} ")
    public List<Question> getQueByMsg(String message, int page, int listNumber);

    /**
     * 获取热门问题
     *
     * @param page
     * @param listNumber
     * @return
     */
    @Select("select * from question  order by like_count desc limit  #{page},#{listNumber} ")
    public Question[] getQueLikes(int page, int listNumber);

    /**
     * 插入问题
     *
     * @param question
     */
    @Insert("insert into question (id,title,description,gmt_create,gmt_modified,creatorId,comment_count,read_count,tag) values" +
            "(#{id},#{title},#{description},#{gmt_create,jdbcType=TIMESTAMP},#{gmt_modified,jdbcType=TIMESTAMP},#{creatorId},#{comment_count},#{read_count},#{tag})")
    public void insertQue(Question question);

    /**
     * 获取所有问题数量
     *
     * @return
     */
    @Select("select * from question  order by gmt_create desc")
    public List<Question> questionList();

    /**
     * 对所有问题根据分页查询
     *
     * @param page
     * @param listNumber
     * @return
     */
    @Select("select * from question order by gmt_create desc limit #{page},#{listNumber} ")
    public List<Question> questionPageList(int page, int listNumber);

    /**
     * 对指定用户的账号按分页查取数据
     *
     * @param page
     * @param listNumber
     * @param creatorId
     * @return
     */
    @Select("select * from question where creatorId=#{creatorId} order by gmt_create desc limit #{page},#{listNumber} ")
    public List<Question> getQuestionPageByUserAccount(int page, int listNumber, Long creatorId);

    /**
     * 获取所有文章总数
     *
     * @return
     */
    @Select("select count(1) from question")
    public int count();

    /**
     * 获取特定用户的问题总数
     *
     * @param creatorId
     * @return
     */
    @Select("select count(1) from question where creatorId=#{creatorId}")
    public int countByUser(Long creatorId);

    /**
     * 根据id查找文章
     *
     * @param id
     * @return
     */
    @Select("select * from question where id=#{id}")
    public Question getQuestionById(int id);

    /**
     * 更新文章
     *
     * @param question
     */
    @Update("UPDATE question SET title=#{title}, description=#{description},tag=#{tag} where id=#{id}")
    void updateQuestion(Question question);

    /**
     * 修改阅读数
     *
     * @param readCount
     * @param questionId
     */
    @Update("UPDATE question SET read_count=#{readCount} where id=#{questionId}")
    void updateQueReadCount(int readCount, int questionId);

    /**
     * 修改点赞数
     *
     * @param like_Count
     * @param questionId
     */
    @Update("UPDATE question SET like_count=#{like_Count} where id=#{questionId}")
    void updateQueLikeCount(int like_Count, int questionId);

    /**
     * 获取指定文章的总赞数
     *
     * @param id
     */
    @Select("select like_count from question where id=#{id}")
    int getLikeCount(int id);

    @Delete("delete from question where id=#{id}")
    void deleteById(int id);
}
