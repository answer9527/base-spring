package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.vo.CommentMsgVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MsgMapper {
    Boolean insertMsg(CommentMsg commentMsg);
    List<CommentMsgVO> getMyCommentMsgList(Integer uid);
}
