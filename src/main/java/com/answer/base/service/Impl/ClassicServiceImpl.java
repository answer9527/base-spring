package com.answer.base.service.Impl;

import com.answer.base.dao.ClassicMapper;
import com.answer.base.entity.Classic;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.ClassicService;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassicServiceImpl implements ClassicService {
    @Autowired
    private ClassicMapper classicMapper;
    @Override
    public Classic getRecommendLatest() {
        Optional<Classic> classicOptional = Optional.ofNullable(classicMapper.getRecommendLatest());
        Classic classic = classicOptional.orElseThrow(()->new ParameterException(50004));
        return classic;
    }

    @Override
    public Classic getRecommendPrevious(Integer id) {
        Optional<Classic> classicOptional = Optional.ofNullable(classicMapper.getRecommendPrevious(id));
        Classic classic = classicOptional.orElseThrow(()->new ParameterException(50004));
        return classic;
    }

    @Override
    public Classic getRecommendNext(Integer id) {
        Optional<Classic> classicOptional = Optional.ofNullable(classicMapper.getRecommendNext(id));
        Classic classic = classicOptional.orElseThrow(()->new ParameterException(50004));
        return classic;
    }

    @Override
    public void insertClassic(Classic classic) {
        Integer count = classicMapper.insertOne(classic);
        if(count==0){
            throw new ParameterException(50001);
        }

    }

    @Override
    public void RecommendClassic(Integer id) {
        Boolean bool = classicMapper.RecommendOne(id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public void removeRecommend(Integer id) {
        Boolean bool = classicMapper.removeRecommend(id);
        if(!bool){
            throw new ParameterException(50003);
        }

    }

    @Override
    public void deleteClassic(Integer id) {
        Boolean bool = classicMapper.deleteOne(id);
        if(!bool){
            throw new ParameterException(50002);
        }
    }
}
