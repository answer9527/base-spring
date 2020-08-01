package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.vo.CommentMsgVO;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UnreadCountVO;

import java.util.List;

public interface MsgService {
    void insertMsg(CommentMsg commentMsg);
//    获取树洞评论制造的消息
    Pager<CommentMsgVO> getMyCommentMsgList(PagingDTO pagingDTO);
//    获取classic评论制造的消息
    Pager<CommentMsgVO> getMyClassicMsgList(PagingDTO pagingDTO);

//  获取我的未读消息数量
    Integer getMyUnReadMsgCount(Integer uid);
//    获取的各分类的未读消息
    UnreadCountVO getUnReadTypeCount(Integer uid);
//    设置消息已读
    void setMsgRead(UidAndIdDTO uidAndIdDTO);
}
