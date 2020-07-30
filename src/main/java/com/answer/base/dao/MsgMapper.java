package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.vo.CommentMsgVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//树洞相关的消息
@Mapper
@Repository
public interface MsgMapper {
    Boolean insertMsg(CommentMsg commentMsg);
    List<CommentMsgVO> getMyCommentMsgList(Integer uid);
//    Boolean insertClassicMsg(CommentMsg commentMsg);
    List<CommentMsgVO> getMyClassicMsgList(Integer uid);
}
