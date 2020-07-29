package com.answer.base.service.Impl;

import com.answer.base.dao.HoleCommentMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.HoleComment;
import com.answer.base.exception.http.AuthException;
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
//        Integer h_com_id =holeComment.getId();
//        SingleHoleVO hole =holeMapper.getHoleById(holeComment.getHid());
//        Msg msg = new Msg();
//        msg.setType(2);
//        msg.setCommentId(h_com_id);
//        msg.setUid(hole.getUid());
//
//        msgMapper.insertMsg(msg);
//        if(holeComment.getPid()!=null){
//
//            msg.setIsRoot(false);
//            msg.setUid(holeComment.getUid_r());
//            msgMapper.insertMsg(msg);
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
