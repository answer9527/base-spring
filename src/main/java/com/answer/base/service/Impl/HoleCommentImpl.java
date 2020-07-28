package com.answer.base.service.Impl;

import com.answer.base.dao.HoleCommentMapper;
import com.answer.base.dao.MsgMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.HoleComment;
import com.answer.base.entity.Msg;
import com.answer.base.exception.http.AuthException;
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
    @Autowired
    private MsgMapper msgMapper;
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
        holeCommentMapper.insertHoleComment(holeComment);
        Integer h_com_id =holeComment.getId();

        Msg msg = new Msg();
        msg.setType(2);
        msg.setCommentId(h_com_id);
        msgMapper.insertMsg(msg);
//        if(holeComment.getPid()==null){
//
//        }else{
//
//        }

//        if(!bool){
//            throw new ParameterException(50003);
//        }
    }

    @Override
    public void delHoleComment(UidAndIdDTO uidAndIdDTO) {
        Boolean bool = holeCommentMapper.delHoleComment(uidAndIdDTO);
        if(!bool){
            throw new AuthException(40004);
        }
    }
}
