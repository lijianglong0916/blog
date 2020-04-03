package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     * 插入comment数据
     *
     * @param comment
     */
    void insertIntoComment(Comment comment);

    /**
     * 根据question id获取该question下的所有comment
     *
     * @param question_id
     * @return
     */
    List<Comment> selectCommentByQuestionId(int question_id);


    /**
     * 根据comment id获取comment
     *
     * @param commentId
     * @return
     */
    Comment getCommentById(int commentId);

    /**
     * 更新点赞数
     *
     * @param id
     * @param sub_count
     */
    void updateCommentSub(int id, int sub_count);

    /**
     * 根据id删除评论
     *
     * @param id
     */
    void deleteComment(int id);
}
