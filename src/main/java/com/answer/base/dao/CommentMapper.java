package com.answer.base.dao;

import com.answer.base.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> selectCommentByClassicId(Integer classicId);
}
