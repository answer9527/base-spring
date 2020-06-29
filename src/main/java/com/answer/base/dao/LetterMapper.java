package com.answer.base.dao;

import com.answer.base.entity.Letter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LetterMapper {
    Boolean insertOne(Letter letter);
    Letter getMyLetter(Integer uid);
    Boolean updateMyLetter(Letter letter);
}
