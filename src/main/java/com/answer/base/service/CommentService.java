package com.answer.base.service;

import com.answer.base.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> selectCommentByClassicId(Integer classic_id);
}
