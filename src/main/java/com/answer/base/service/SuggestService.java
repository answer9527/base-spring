package com.answer.base.service;


import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.ReplySuggestDTO;
import com.answer.base.entity.Suggest;
import com.answer.base.vo.Pager;
import com.answer.base.vo.SuggestVO;

public interface SuggestService {
    void insertSuggest(Suggest suggest);
    void replySuggest(ReplySuggestDTO replySuggestDTO);
    Pager<SuggestVO> getAll(PagingDTO pagingDTO);
    void delSuggest(Integer id);
}
