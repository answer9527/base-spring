package com.answer.base.dao;


import com.answer.base.entity.Classic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassicMapper {
//    获取最新推荐
    Classic getRecommendLatest();
//    获取上一个推荐
    Classic getRecommendPrevious(Integer id);
//    获取下一个推荐
    Classic getRecommendNext(Integer id);

//    插入一条数据
    Integer insertOne(Classic classic);

//    设置推荐一条数据
    Integer RecommendOne(Integer id);

//    取消一条推荐
    Integer removeRecommend(Integer id);

//    删除一条数据
    Integer deleteOne(Integer id);

}
