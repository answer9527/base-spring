package com.answer.base.dao;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Hole;
import com.answer.base.vo.RandHoleVO;
import com.answer.base.vo.SingleHoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HoleMapper {
    Boolean insertHole(Hole hole);
    List<Hole> getMyHole(PagingDTO pagingDTO);
    List<RandHoleVO> getRandHole(PagingDTO pagingDTO);
    SingleHoleVO getHoleById(Integer id);
    Boolean delMyHole(UidAndIdDTO uidAndIdDTO);
    List<SingleHoleVO> getHoleList();
    Boolean powerDelHole(Integer id);
}
