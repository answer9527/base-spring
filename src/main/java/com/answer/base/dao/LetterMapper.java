package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Letter;
import com.answer.base.vo.LetterVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LetterMapper {
    Boolean insertOne(Letter letter);
    Letter getMyLetter(Integer uid);
    Boolean updateMyLetter(Letter letter);
    List<LetterVO> getLetterList(PagingDTO pagingDTO);
}
