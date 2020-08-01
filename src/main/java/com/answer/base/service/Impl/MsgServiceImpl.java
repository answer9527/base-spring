package com.answer.base.service.Impl;

import com.answer.base.dao.MsgMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.MsgService;
import com.answer.base.vo.CommentMsgVO;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UnreadCountVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgMapper msgMapper;
    @Override
    public void insertMsg(CommentMsg commentMsg) {
        Boolean bool = msgMapper.insertMsg(commentMsg);
        if(!bool){
            throw new ParameterException(10001);
        }
    }

    @Override
    public Pager<CommentMsgVO> getMyCommentMsgList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<CommentMsgVO> pageInfo = new PageInfo<>(msgMapper.getMyCommentMsgList(pagingDTO.getKey()));
        return new Pager(pageInfo);
    }

    @Override
    public Pager<CommentMsgVO> getMyClassicMsgList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<CommentMsgVO> pageInfo = new PageInfo<>(msgMapper.getMyClassicMsgList(pagingDTO.getKey()));
        return new Pager(pageInfo);
    }

    @Override
    public Integer getMyUnReadMsgCount(Integer uid) {
        return msgMapper.getMyUnReadMsgCount(uid);
    }

    @Override
    public UnreadCountVO getUnReadTypeCount(Integer uid) {
        return msgMapper.getUnReadTypeCount(uid);
    }

    @Override
    public void setMsgRead(UidAndIdDTO uidAndIdDTO) {
        Boolean bool = msgMapper.setMsgRead(uidAndIdDTO);
        if(!bool){
            throw new ParameterException(50003);
        }
    }
}
