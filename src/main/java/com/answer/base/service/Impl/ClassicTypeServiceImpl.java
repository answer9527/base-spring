package com.answer.base.service.Impl;

import com.answer.base.dao.ClassicTypeMapper;
import com.answer.base.entity.ClassicType;
import com.answer.base.service.ClassicTypeService;
import com.answer.base.vo.SortTotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassicTypeServiceImpl implements ClassicTypeService {
    @Autowired
    private ClassicTypeMapper classicTypeMapper;
    @Override
    public List<ClassicType> getClassicTypeAll() {
        return classicTypeMapper.getClassicTypeAll();
    }

    @Override
    public List<SortTotalVO> getClassicTypeSortTotal() {
        return classicTypeMapper.getClassicTypeSortTotal();
    }
}
