package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.ReplySuggestDTO;
import com.answer.base.entity.Suggest;
import com.answer.base.vo.SuggestVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SuggestMapper {
    Boolean insertSuggest(Suggest suggest);
    Boolean replySuggest(ReplySuggestDTO replySuggestDTO);
    List<SuggestVO> getAll(String keyword);
    Boolean delSuggest(Integer id);
}
