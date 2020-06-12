package com.answer.base.service.Impl;

import com.answer.base.dao.ClassicMapper;
import com.answer.base.entity.Classic;
import com.answer.base.service.ClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassicServiceImpl implements ClassicService {
    @Autowired
    private ClassicMapper classicMapper;
    @Override
    public Classic getRecommendLatest() {
        return classicMapper.getRecommendLatest();
    }

    @Override
    public Classic getRecommendPrevious(Integer id) {
        return classicMapper.getRecommendPrevious(id);
    }
}
