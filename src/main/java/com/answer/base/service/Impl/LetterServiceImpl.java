package com.answer.base.service.Impl;

import com.answer.base.dao.LetterMapper;
import com.answer.base.entity.Letter;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
