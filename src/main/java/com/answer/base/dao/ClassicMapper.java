package com.answer.base.dao;


import com.answer.base.dto.ChangeLikeCountDTO;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.dto.UserLikeUnlikeClassicDTO;
import com.answer.base.entity.Classic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ClassicMapper {
//    获取最新推荐
    Classic getRecommendLatest();
//    获取上一个推荐
    Classic getRecommendPrevious(Integer id);
//    获取下一个推荐
    Classic getRecommendNext(Integer id);


//    插入一条数据  设置为Integer,Long返回是插入的数量  设置Boolean返回是否插入成功    update 和delete方法也是同理
    Integer insertOne(Classic classic);

//    设置推荐一条数据
    Boolean RecommendOne(Integer id);

//    取消一条推荐
    Boolean removeRecommend(Integer id);

//    删除一条数据
    Boolean deleteOne(Integer id);

//    查找我喜欢的classic
    List<Classic> getMyLike(Integer uid);

//    设置我喜欢
    Boolean setLike(UserLikeUnlikeClassicDTO userLikeClassicDTO);

//    设置更新like_count
    Boolean updateLikeCount(ChangeLikeCountDTO changeLikeCountDTO);

//    取消我喜欢
    Boolean cancelLike(UserLikeUnlikeClassicDTO userUnlikeClassicDTO);

//    按照类型查找列表
    List<Classic> getListByType(PagingDTO pagingDTO);

//    根据Id获取详情
    Classic getDetailById(Integer id);

//    根据用户的id 和classic 的id 判断是否为喜欢
    Boolean getLikeStatus(UidAndIdDTO uidAndIdDTO);

}
