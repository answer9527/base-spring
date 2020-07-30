package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Comment;
import com.answer.base.vo.Pager;

import java.util.List;

public interface CommentService {
    Pager<Comment> selectCommentByClassicId(PagingDTO pagingDTO);
    Comment insertComment(Comment comment);
    void delComment(UidAndIdDTO uidAndIdDTO);
}
