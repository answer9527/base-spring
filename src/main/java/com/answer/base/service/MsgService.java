package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.vo.CommentMsgVO;
import com.answer.base.vo.Pager;

import java.util.List;

public interface MsgService {
    void insertMsg(CommentMsg commentMsg);
    Pager<CommentMsgVO> getMyCommentMsgList(PagingDTO pagingDTO);
}
