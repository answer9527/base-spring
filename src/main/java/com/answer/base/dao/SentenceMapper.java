package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SentenceMapper {
    Sentence getRandSentence();
    List<Sentence> getSentenceList(PagingDTO pagingDTO);
    Boolean insertSentence(Sentence sentence);
    Boolean delSentence(Integer id);
}
