package com.answer.base.service.Impl;

import com.answer.base.dao.SentenceMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Sentence;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.SentenceService;
import com.answer.base.vo.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public Pager<Sentence> getSentenceList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<Sentence> pageInfo = new PageInfo<>(sentenceMapper.getSentenceList(pagingDTO));
        return new Pager<>(pageInfo);
    }

    @Override
    public void insertSentence(Sentence sentence) {
        Boolean bool = sentenceMapper.insertSentence(sentence);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public void delSentence(Integer id) {
        Boolean bool = sentenceMapper.delSentence(id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }
}
