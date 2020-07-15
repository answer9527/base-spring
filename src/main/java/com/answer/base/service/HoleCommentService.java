package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.HoleComment;

import java.util.List;

public interface HoleCommentService {
    List<HoleComment> getHoleCommentByHid(PagingDTO pagingDTO);
    void insertHoleComment(HoleComment holeComment);
}
