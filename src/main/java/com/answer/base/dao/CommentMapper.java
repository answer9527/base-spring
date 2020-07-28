package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> selectCommentByClassicId(Integer cid);
    Boolean insertComment(Comment comment);
    Boolean delComment(UidAndIdDTO uidAndIdDTO);
}
