package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Letter;
import com.answer.base.vo.LetterVO;
import com.answer.base.vo.MonthCountVO;
import com.answer.base.vo.Pager;

import java.util.List;

public interface LetterService {
    void insertOne(Letter letter);
    Letter getMyLetter(Integer uid);
    Pager<Letter> getMyLetterList(PagingDTO pagingDTO);
    void updateLetter(Letter letter);
    Pager<LetterVO> getLetterList(PagingDTO pagingDTO);
    List<MonthCountVO> getLetterMonthPlan(Integer y,Integer m);
    Pager<LetterVO> getLetterListByTime(Integer y,Integer m,Integer d,PagingDTO pagingDTO);
    LetterVO getPlanLetterById(Integer id);
    void setLetterOverById(Integer id);
    LetterVO getPublicLetterById(Integer id);
}
