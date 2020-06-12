package com.answer.base.service;

import com.answer.base.entity.Classic;

public interface ClassicService {
//    获取最新推荐
    Classic getRecommendLatest();
//    获取推荐上一个
    Classic getRecommendPrevious(Integer id);

}
