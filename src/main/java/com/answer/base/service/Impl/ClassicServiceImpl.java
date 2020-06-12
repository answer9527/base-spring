package com.answer.base.service.Impl;

import com.answer.base.dao.ClassicMapper;
import com.answer.base.entity.Classic;
import com.answer.base.service.ClassicService;
import com.sun.org.apache.bcel.internal.generic.RET;
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

    @Override
    public Classic getRecommendNext(Integer id) {
        return classicMapper.getRecommendNext(id);
    }

    @Override
    public Integer insertClassic(Classic classic) {
        return classicMapper.insertOne(classic);
    }

    @Override
    public Integer RecommendClassic(Integer id) {
       return classicMapper.RecommendOne(id);
    }

    @Override
    public Integer removeRecommend(Integer id) {
        return classicMapper.removeRecommend(id);
    }

    @Override
    public Integer deleteClassic(Integer id) {
        return classicMapper.deleteOne(id);
    }
}
