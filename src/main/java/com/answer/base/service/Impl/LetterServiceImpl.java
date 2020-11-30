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
    public Pager<Letter> getMyLetterList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<Letter> letterPageInfo = new PageInfo<>(letterMapper.getMyLetterList(pagingDTO.getKey()));
        return new Pager<>(letterPageInfo);
    }

    @Override
    public LetterVO getMyLetterDetail(Integer uid, Integer id) {
        LetterVO letterVO = letterMapper.getMyLetterDetail(uid, id);
        return letterVO;
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

    @Override
    public LetterVO getPlanLetterById(Integer id) {
        Optional<LetterVO> letterVOOptional = Optional.ofNullable(letterMapper.getPlanLetterById(id));
        LetterVO letterVO = letterVOOptional.orElseThrow(()->new ParameterException(50005));
        return letterVO;
    }

    @Override
    public void setLetterOverById(Integer id) {
        Boolean bool = letterMapper.setLetterOverById(id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public LetterVO getPublicLetterById(Integer id) {
        Optional<LetterVO> letterVOOptional = Optional.ofNullable(letterMapper.getPublicLetterById(id));
        LetterVO letterVO = letterVOOptional.orElseThrow(()->new ParameterException(50006));
        return letterVO;
    }

    @Override
    public void delMyLetter(Integer uid, Integer id) {
        Boolean bool = letterMapper.delMyLetter(uid,id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public void setLetterImage(Integer id, String image) {
        Boolean bool = letterMapper.setLetterImage(id,image);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

}
