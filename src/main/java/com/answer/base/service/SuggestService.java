package com.answer.base.service;


import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.ReplySuggestDTO;
import com.answer.base.entity.Suggest;
import com.answer.base.vo.Pager;

import java.util.List;

public interface SuggestService {
    void insertSuggest(Suggest suggest);
    void replySuggest(ReplySuggestDTO replySuggestDTO);
    Pager<Suggest> getAll(PagingDTO pagingDTO);
}
