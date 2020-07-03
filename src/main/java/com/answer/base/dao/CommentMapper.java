package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> selectCommentByClassicId(PagingDTO pagingDTO);
    Boolean insertComment(Comment comment);
}