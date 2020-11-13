package com.answer.base.service.Impl;

import com.answer.base.dao.OtherMapper;
import com.answer.base.service.OtherService;
import com.answer.base.vo.SortTotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    private OtherMapper OtherMapper;
    @Override
    public List<SortTotalVO> getDashboardSortTotal() {
        return OtherMapper.getDashboardSortTotal();
    }
}
