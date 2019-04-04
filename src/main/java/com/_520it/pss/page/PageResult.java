package com._520it.pss.page;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
@Getter
public class PageResult {

    private List listData;

    private Integer totalCount;

    private Integer currentPage=1;

    private Integer pageSize=5;

    private Integer nextPage;

    private Integer prevPage;

    private Integer totalPage;

    public PageResult(List listData, Integer totalCount, Integer currentPage, Integer pageSize) {
        this.listData = listData;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalCount%pageSize==0? totalCount/pageSize:totalCount/pageSize+1;
        this.nextPage = currentPage+1>totalPage?totalPage:currentPage+1;
        this.prevPage=currentPage-1>0?currentPage-1:1;
    }

    public static final PageResult EMPTY = new PageResult(Collections.EMPTY_LIST,0,1,5);
}
