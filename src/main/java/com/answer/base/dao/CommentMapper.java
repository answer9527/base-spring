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
    Integer insertComment(Comment comment);
//    删除我的评论
    Boolean delComment(UidAndIdDTO uidAndIdDTO);
//    管理员删除评论
    Boolean powerDelComment(Integer id);
}
