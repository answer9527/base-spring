package com.answer.base.service;

import com.answer.base.entity.Classic;

public interface ClassicService {
//    获取最新推荐
    Classic getRecommendLatest();
//    获取推荐上一个
    Classic getRecommendPrevious(Integer id);
//    获取推荐的下一个
    Classic getRecommendNext(Integer id);

//    插入一条数据
    Integer insertClassic(Classic classic);

//    设置推荐
    Integer RecommendClassic(Integer id);

//   取消推荐
    Integer removeRecommend(Integer id);

//    删除一条
    Integer deleteClassic(Integer id);

}
