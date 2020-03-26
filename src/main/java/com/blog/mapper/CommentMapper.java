package com.blog.mapper;/*
 *@author:
 *@time
 */

import com.blog.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 插入新的回复/评论信息
     *
     * @param comment
     */
    @Insert("insert into comment (commentText,creatorId,question_id,comment_create,comment_count,sub_count)values(#{commentText},#{creatorId},#{question_id},#{comment_create},#{comment_count},#{sub_count})")
    public void insertComment(Comment comment);

    /**
     * 查询该文章下面的所有评论
     *
     * @param question_id
     * @return
     */
    @Select("select *from comment where question_id=#{question_id}")
    public List<Comment> selectByQuestionId(int question_id);


    /**
     * 跟据评论id查询单个评论信息
     *
     * @param commentId
     * @return
     */
    @Select("select *from comment where id=#{commentId}")
    Comment getCommentById(int commentId);

    /**
     * @param id
     * @param sub_count
     */
    @Update("UPDATE comment SET sub_count=#{sub_count} where id=#{id}")
    void updateCommentSubById(int id, int sub_count);

    /**
     * 根据id删除comment
     *
     * @param id
     */
    @Delete("delete from comment where id=#{id}")
    void deleteComment(int id);
}
