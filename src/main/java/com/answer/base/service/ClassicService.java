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
    void insertClassic(Classic classic);

//    设置推荐
    void RecommendClassic(Integer id);

//   取消推荐
    void removeRecommend(Integer id);

//    删除一条
    void deleteClassic(Integer id);

}
