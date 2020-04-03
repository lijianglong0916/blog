package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.entity.Comment;
import com.blog.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 插入comment数据
     *
     * @param comment
     */
    @Override
    public void insertIntoComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    /**
     * 根据question id获取该question下的所有comment
     *
     * @param question_id
     * @return
     */
    @Override
    public List<Comment> selectCommentByQuestionId(int question_id) {
        List<Comment> comments = commentMapper.selectByQuestionId(question_id);
        return comments;
    }

    /**
     * 根据comment id获取comment
     *
     * @param commentId
     * @return
     */
    @Override
    public Comment getCommentById(int commentId) {
        return commentMapper.getCommentById(commentId);
    }

    /**
     * 更新点赞数
     *
     * @param id
     * @param sub_count
     */
    @Override
    public void updateCommentSub(int id, int sub_count) {
        commentMapper.updateCommentSubById(id, sub_count);
    }

    /**
     * 根据id删除评论
     *
     * @param id
     */
    @Override
    public void deleteComment(int id) {
        commentMapper.deleteComment(id);
    }
}
