package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.HoleComment;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HoleCommentMapper {
//    List<HoleComment> getHoleCommentByHid(PagingDTO pagingDTO);
    Page<HoleComment> getHoleCommentByHid(Integer hid);
    Integer insertHoleComment(HoleComment holeComment);
    Boolean delHoleComment(UidAndIdDTO uidAndIdDTO);
    Boolean powerDelHoleComment(Integer id);
}
