package com.answer.base.dao;

import com.answer.base.vo.SortTotalVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OtherMapper {
    List<SortTotalVO> getDashboardSortTotal();
}
