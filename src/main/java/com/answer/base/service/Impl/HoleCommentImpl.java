package com.answer.base.service.Impl;

import com.answer.base.dao.HoleCommentMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.HoleComment;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.HoleCommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoleCommentImpl implements HoleCommentService {
    @Autowired
    private HoleCommentMapper holeCommentMapper;
    @Override
    public Page<HoleComment> getHoleCommentByHid(PagingDTO pagingDTO) {
        Integer hid = pagingDTO.getKey();
        Page<HoleComment> holeComments = holeCommentMapper.getHoleCommentByHid(hid);
//        List<HoleComment> holeComments = holeCommentMapper.getHoleCommentByHid(pagingDTO);
//        return holeCommentMapper.getHoleCommentByHid(pagingDTO);
        return holeComments;
    }

    @Override
    public void insertHoleComment(HoleComment holeComment) {
        Boolean bool = holeCommentMapper.insertHoleComment(holeComment);
        if(!bool){
            throw new ParameterException(50003);
        }
    }
}
