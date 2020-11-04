package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Sentence;
import com.answer.base.vo.Pager;

import java.util.List;

public interface SentenceService {
    Sentence getRandSentence();
    Pager<Sentence> getSentenceList(PagingDTO pagingDTO);
    void insertSentence(Sentence sentence);
    void delSentence(Integer id);
}
