package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Comment;
import com.answer.base.vo.Pager;

import java.util.List;

public interface CommentService {
    Pager<Comment> selectCommentByClassicId(PagingDTO pagingDTO);
    void insertComment(Comment comment);
}
