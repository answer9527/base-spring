package com.answer.base.service;


import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.ReplySuggestDTO;
import com.answer.base.entity.Suggest;

import java.util.List;

public interface SuggestService {
    void insertSuggest(Suggest suggest);
    void replySuggest(ReplySuggestDTO replySuggestDTO);
    List<Suggest> getAll(PagingDTO pagingDTO);
}
