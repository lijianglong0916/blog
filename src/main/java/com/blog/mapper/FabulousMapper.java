package com.blog.mapper;/*
 *@author:
 *@time
 */

import com.blog.entity.Fabulous;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FabulousMapper {
    /**
     * 查询当前用户所有点过赞的文章并分页
     *
     * @param user_id
     * @return
     */
    @Select("select *from fabulous where user_id=#{user_id} limit #{page},#{listNumber} ")
    public Fabulous[] selectFabulous(int page, int listNumber, Long user_id);

    /**
     * 通过文章id和点赞用户确定赞过的确定数据
     *
     * @param user_id
     * @param question_id
     * @return
     */
    @Select("select *from fabulous where user_id=#{user_id} and question_id=#{question_id}")
    public Fabulous isExistFabulous(Long user_id, int question_id);

    /**
     * 通过文章id赞过的确定数据
     *
     * @param question_id
     * @return
     */
    @Select("select *from fabulous where question_id=#{question_id}")
    public List<Fabulous> getFabulousByQue(int question_id);

    /**
     * 向表中插入点赞信息
     *
     * @param fabulous
     */
    @Insert("insert into fabulous(user_id,question_id,exist_state) values (#{user_id},#{question_id},#{exist_state})")
    public void insertFabulous(Fabulous fabulous);

    /**
     * 逻辑删除点赞信息
     *
     * @param id
     */
    @Update("delete from fabulous where  id=#{id}")
    public void removeFabulous(int id);

    /**
     * 获取用户点赞文章总数
     *
     * @param user_id
     * @return
     */
    @Select("select count(1) from fabulous where user_id=#{user_id} and exist_state=true")
    public int count(Long user_id);

}
