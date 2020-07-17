package com.answer.base.service;

import com.answer.base.dto.ChangeLikeCountDTO;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Classic;
import com.answer.base.vo.Pager;

import java.util.List;
import java.util.Map;

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

//    设置喜欢
    void setLike(Integer uid,Integer cid);

//    取消喜欢
    void cancelLike(Integer uid,Integer cid);

//    设置更新likeCount
    void setLikeCount(ChangeLikeCountDTO changeLikeCountDTO);

//    获取我的点喜欢的列表
    Pager<Classic> getMyLike(PagingDTO pagingDTO);

//    按照类型type查找列表
    List<Classic> getListByType(PagingDTO pagingDTO);

//    根据id获取详情
    Classic getDetailById(Integer id);

//    根据用户的id 和classic 的id 判断是否为喜欢
    Boolean getLikeStatus(UidAndIdDTO uidAndIdDTO);
}
