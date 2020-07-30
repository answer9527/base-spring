package com.answer.base.service.Impl;

import com.answer.base.dao.CommentMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Comment;
import com.answer.base.exception.http.AuthException;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.CommentService;
import com.answer.base.vo.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Pager<Comment> selectCommentByClassicId(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<Comment> pageInfo = new PageInfo<>(commentMapper.selectCommentByClassicId(pagingDTO.getKey()));
        return new Pager<>(pageInfo);
    }

    @Override
    public Comment insertComment(Comment comment) {
        commentMapper.insertComment(comment);
        return comment;
    }

    @Override
    public void delComment(UidAndIdDTO uidAndIdDTO) {
        Boolean bool = commentMapper.delComment(uidAndIdDTO);
        if(!bool){
            throw new AuthException(40004);
        }
    }
}
