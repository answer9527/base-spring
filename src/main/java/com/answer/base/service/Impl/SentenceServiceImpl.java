package com.answer.base.service.Impl;

import com.answer.base.dao.SentenceMapper;
import com.answer.base.entity.Sentence;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SentenceServiceImpl implements SentenceService {
    @Autowired
    private SentenceMapper sentenceMapper;
    @Override
    public Sentence getRandSentence() {
        Optional<Sentence> sentenceOptional = Optional.ofNullable(sentenceMapper.getRandSentence());
        Sentence sentence = sentenceOptional.orElseThrow(()->new ParameterException(50004));
        return sentence;
    }
}
