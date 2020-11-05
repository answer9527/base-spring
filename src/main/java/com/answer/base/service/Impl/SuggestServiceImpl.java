package com.answer.base.service.Impl;

import com.answer.base.dao.SuggestMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.ReplySuggestDTO;
import com.answer.base.entity.Suggest;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.SuggestService;
import com.answer.base.vo.Pager;
import com.answer.base.vo.SuggestVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestServiceImpl implements SuggestService {
    @Autowired
    private SuggestMapper suggestMapper;
    @Override
    public void insertSuggest(Suggest suggest) {
        Boolean bool = suggestMapper.insertSuggest(suggest);
        if(!bool){
           throw new ParameterException(50001);
        }
    }

    @Override
    public void replySuggest(ReplySuggestDTO replySuggestDTO) {
        Boolean bool = suggestMapper.replySuggest(replySuggestDTO);
        if(!bool){
            throw new ParameterException(50001);
        }
    }

    @Override
    public Pager<SuggestVO> getAll(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<SuggestVO> pageInfo = new PageInfo<>(suggestMapper.getAll(pagingDTO.getKeyword()));
        return new Pager<>(pageInfo);
    }

    @Override
    public void delSuggest(Integer id) {
        Boolean bool = suggestMapper.delSuggest(id);
        if(!bool){
            throw new ParameterException(50001);
        }
    }
}
