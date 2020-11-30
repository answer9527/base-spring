package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Letter;
import com.answer.base.vo.LetterVO;
import com.answer.base.vo.MonthCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LetterMapper {
    Boolean insertOne(Letter letter);

    Letter getMyLetter(Integer uid);

    List<Letter> getMyLetterList(Integer uid);

    LetterVO getMyLetterDetail(@Param("uid") Integer uid,@Param("id") Integer id);

    Boolean updateMyLetter(Letter letter);

    List<LetterVO> getLetterList(PagingDTO pagingDTO);

    List<MonthCountVO> getLetterMonthPlan(@Param("y") Integer y, @Param("m") Integer m);

    List<LetterVO> getLetterListByTime(@Param("y") Integer y, @Param("m") Integer m, @Param("d") int d);

    //    获取待投递的信件内容
    LetterVO getPlanLetterById(@Param("id") Integer id);

    //    设置信件的投递完成
    Boolean setLetterOverById(@Param("id") Integer id);

//    根据id 获取可查看的信件 即  用户选择手抄版  和音频版
    LetterVO getPublicLetterById(@Param("id") Integer id);

//    删除我的某封信件
    Boolean delMyLetter(@Param("uid") Integer uid,@Param("id") Integer id);

//    设置手抄信的图片
    Boolean setLetterImage(@Param("id") Integer id,@Param("image") String image);
}