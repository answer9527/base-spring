package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Hole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HoleMapper {
    Boolean insertHole(Hole hole);
    List<Hole> getMyHole(PagingDTO pagingDTO);
}
