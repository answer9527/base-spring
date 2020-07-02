package com.answer.base.service.Impl;

import com.answer.base.dao.CommentMapper;
import com.answer.base.entity.Comment;
import com.answer.base.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> selectCommentByClassicId(Integer classic_id) {
        return commentMapper.selectCommentByClassicId(classic_id);
    }
}
