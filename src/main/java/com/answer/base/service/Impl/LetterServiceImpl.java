package com.answer.base.service.Impl;

import com.answer.base.dao.LetterMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Letter;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.LetterService;
import com.answer.base.vo.LetterVO;
import com.answer.base.vo.MonthCountVO;
import com.answer.base.vo.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetterServiceImpl implements LetterService {
    @Autowired
    private LetterMapper letterMapper;
    @Override
    public void insertOne(Letter letter) {
       Boolean bool = letterMapper.insertOne(letter);
       if(!bool) {
           throw new ParameterException(50003);
       }
    }

    @Override
    public Letter getMyLetter(Integer uid) {
        Optional<Letter> letterOptional = Optional.ofNullable(letterMapper.getMyLetter(uid));
        Letter letter = letterOptional.orElseThrow(()->new ParameterException(50004));
        return letter;
    }

    @Override
    public void updateLetter(Letter letter) {
        Boolean bool = letterMapper.updateMyLetter(letter);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public Pager<LetterVO> getLetterList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<LetterVO> letterPageInfo = new PageInfo<>(letterMapper.getLetterList(pagingDTO));
        return new Pager<>(letterPageInfo);
    }

    @Override
    public List<MonthCountVO> getLetterMonthPlan(Integer y, Integer m) {
        List<MonthCountVO> countVOList = letterMapper.getLetterMonthPlan(y,m);
        return countVOList;
    }

    @Override
    public Pager<LetterVO> getLetterListByTime(Integer y, Integer m, Integer d, PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<LetterVO> letterVOPageInfo = new PageInfo<>(letterMapper.getLetterListByTime(y,m,d));
        return new Pager<>(letterVOPageInfo);
    }

}
