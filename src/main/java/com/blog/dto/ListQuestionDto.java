package com.blog.dto;/*
 *@author:
 *@time
 */

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章分页工具类
 */
@Data

public class ListQuestionDto {
    private List<QuestionDto> questionDtos;
    private boolean firstPage;
    private boolean previousPage;
    private boolean nextPage;
    private boolean lastPage;
    private int pageNumber;
    private int page;
    private List pageList;

    public void setPageList(int page, int totalNumber, int size) {
        this.page = page;
        this.pageList = new ArrayList();
        //获取总的页数
        if (totalNumber % size == 0) {
            pageNumber = totalNumber / size;
        } else {
            pageNumber = totalNumber / size + 1;
        }
        if (page > pageNumber) {
            page = pageNumber;
        }
        if (page <= 0) {
            page = 1;
        }
        for (int i = 0; i < 3 && (page + i) <= pageNumber && page > 0; i++) {
            pageList.add(page + i);
        }
        for (int i = 1; i <= 3 && page - i > 0 && page - i < pageNumber; i++) {
            pageList.add(0, page - i);
        }
        //设置首页跳转是否可见
        if (page > 3) {
            firstPage = true;
        } else {
            firstPage = false;
        }
        //设置前一页跳转是否可见
        if (page != 1) {
            previousPage = true;
        } else {
            previousPage = false;
        }
        //设置最后一页跳转是否可见
        if (page + 2 < pageNumber) {
            lastPage = true;
        } else {
            lastPage = false;
        }
        //设置下一页跳转是否可见
        if (page < pageNumber) {
            nextPage = true;
        } else {
            nextPage = false;
        }

    }
}
