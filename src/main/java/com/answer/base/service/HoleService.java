package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Hole;
import com.answer.base.vo.Pager;
import com.answer.base.vo.RandHoleVO;
import com.answer.base.vo.SingleHoleVO;

import java.util.List;

public interface HoleService {
    void insertHole(Hole hole);
    List<Hole> getMyHole(PagingDTO pagingDTO);
    List<RandHoleVO> getRandHole(PagingDTO pagingDTO);
    SingleHoleVO getHoleById(Integer id);
    void delHole(UidAndIdDTO uidAndIdDTO);
    Pager<SingleHoleVO> getHoleList(PagingDTO pagingDTO);
    void powerDelHole(Integer id);

}
