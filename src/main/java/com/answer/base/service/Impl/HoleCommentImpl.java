package com.answer.base.service.Impl;

import com.answer.base.dao.HoleCommentMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.HoleComment;
import com.answer.base.service.HoleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoleCommentImpl implements HoleCommentService {
    @Autowired
    private HoleCommentMapper holeCommentMapper;
    @Override
    public List<HoleComment> getHoleCommentByHid(PagingDTO pagingDTO) {
        return holeCommentMapper.getHoleCommentByHid(pagingDTO);
    }
}
