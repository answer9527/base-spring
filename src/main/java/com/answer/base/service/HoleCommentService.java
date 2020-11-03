package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.HoleComment;

import java.util.List;

public interface HoleCommentService {
    List<HoleComment> getHoleCommentByHid(PagingDTO pagingDTO);
    HoleComment insertHoleComment(HoleComment holeComment);
    void delHoleComment(UidAndIdDTO uidAndIdDTO);
    void powerDelHoleComment(Integer id);
}
