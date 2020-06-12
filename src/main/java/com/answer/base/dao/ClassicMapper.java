package com.answer.base.dao;


import com.answer.base.entity.Classic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassicMapper {
//    获取最新推荐
    Classic getRecommendLatest();
//    获取上一个
    Classic getRecommendPrevious(Integer id);
}
