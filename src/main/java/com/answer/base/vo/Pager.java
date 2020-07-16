package com.answer.base.vo;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Pager<T> {
    private Boolean hasNextPage;
    private Boolean hasPrePage;
    private Integer pageNum;
    private Integer pageSize;
    private Integer pages;
    private Long total;
    private List<T> list;

    public void initPageParameters(PageInfo<T> pageInfo){
        this.hasNextPage= pageInfo.isHasNextPage();
        this.hasPrePage= pageInfo.isHasPreviousPage();
        this.pageNum= pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.total = pageInfo.getTotal();
    }
    public Pager(PageInfo<T> pageInfo){
        this.initPageParameters(pageInfo);
        this.list=pageInfo.getList();
    }
}
