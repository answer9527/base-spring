package com.answer.base.service.Impl;

import com.answer.base.dao.HoleCommentMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.HoleComment;
import com.answer.base.exception.http.AuthException;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.HoleCommentService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public HoleComment insertHoleComment(HoleComment holeComment) {
        holeCommentMapper.insertHoleComment(holeComment);
        return holeComment;
    }

    @Override
    public void delHoleComment(UidAndIdDTO uidAndIdDTO) {
        Boolean bool = holeCommentMapper.delHoleComment(uidAndIdDTO);
        if(!bool){
            throw new AuthException(40004);
        }
    }

    @Override
    public void powerDelHoleComment(Integer id) {
        Boolean bool = holeCommentMapper.powerDelHoleComment(id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }
}
