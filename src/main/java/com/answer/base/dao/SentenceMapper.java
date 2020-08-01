package com.answer.base.dao;

import com.answer.base.entity.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SentenceMapper {
    Sentence getRandSentence();
}
