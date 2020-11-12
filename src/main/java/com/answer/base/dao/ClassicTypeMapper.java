package com.answer.base.dao;

import com.answer.base.entity.ClassicType;
import com.answer.base.vo.SortTotalVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassicTypeMapper {
//    获取所有的classic的类型
    List<ClassicType> getClassicTypeAll();

//    获取classic按类型的统计数量
    List<SortTotalVO> getClassicTypeSortTotal();
}
