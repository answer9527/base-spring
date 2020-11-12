package com.answer.base.service;

import com.answer.base.entity.Classic;
import com.answer.base.entity.ClassicType;
import com.answer.base.vo.SortTotalVO;

import java.util.List;

public interface ClassicTypeService {
//    获取classic类型列表
    List<ClassicType> getClassicTypeAll();
//    获取classic各类型的数量统计
    List<SortTotalVO> getClassicTypeSortTotal();
}
