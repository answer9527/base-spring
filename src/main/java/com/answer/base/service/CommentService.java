package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> selectCommentByClassicId(PagingDTO pagingDTO);
    void insertComment(Comment comment);
}
