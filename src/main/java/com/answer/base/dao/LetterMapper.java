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
    Boolean updateMyLetter(Letter letter);
    List<LetterVO> getLetterList(PagingDTO pagingDTO);
    List<MonthCountVO> getLetterMonthPlan(@Param("y") Integer y,@Param("m") Integer m);
    List<LetterVO> getLetterListByTime(@Param("y") Integer y,@Param("m") Integer m,@Param("d") int d);
//    获取待投递的信件内容
    LetterVO getPlanLetterById(@Param("id") Integer id);
//    设置信件的投递完成
    Boolean setLetterOverById(@Param("id") Integer id);
}
