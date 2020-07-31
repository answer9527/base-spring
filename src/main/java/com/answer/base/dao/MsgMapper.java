package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.vo.CommentMsgVO;
import com.answer.base.vo.UnreadCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MsgMapper {
    Boolean insertMsg(CommentMsg commentMsg);
    List<CommentMsgVO> getMyCommentMsgList(Integer uid);
//    Boolean insertClassicMsg(CommentMsg commentMsg);
    List<CommentMsgVO> getMyClassicMsgList(Integer uid);
//    获取未读消息数量的总数
    Integer getMyUnReadMsgCount(Integer uid);

    UnreadCountVO getUnReadTypeCount(Integer uid);
}
