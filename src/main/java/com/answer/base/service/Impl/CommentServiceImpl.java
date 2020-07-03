package com.answer.base.service.Impl;

import com.answer.base.dao.CommentMapper;
import com.answer.base.entity.Comment;
import com.answer.base.exception.http.ParameterException;
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

    @Override
    public void insertComment(Comment comment) {
        Boolean bool = commentMapper.insertComment(comment);
        if(!bool){
            throw new ParameterException(50001);
        }
    }
}
