package com.answer.base.service.Impl;

import com.answer.base.dao.HoleMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Hole;
import com.answer.base.exception.http.AuthException;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.HoleService;
import com.answer.base.vo.Pager;
import com.answer.base.vo.RandHoleVO;
import com.answer.base.vo.SingleHoleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoleServiceImpl implements HoleService {
    @Autowired
    private HoleMapper holeMapper;
    @Override
    public void insertHole(Hole hole) {
        Boolean bool = holeMapper.insertHole(hole);
    }

    @Override
    public List<Hole> getMyHole(PagingDTO pagingDTO) {
        return holeMapper.getMyHole(pagingDTO);
    }

    @Override
    public List<RandHoleVO> getRandHole(PagingDTO pagingDTO) {
        return holeMapper.getRandHole(pagingDTO);
    }

    @Override
    public SingleHoleVO getHoleById(Integer id) {
        return holeMapper.getHoleById(id);
    }

    @Override
    public void delHole(UidAndIdDTO uidAndIdDTO) {
        Boolean bool = holeMapper.delMyHole(uidAndIdDTO);
        if(!bool){
            throw new AuthException(40004);
        }
    }

    @Override
    public Pager<SingleHoleVO> getHoleList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<SingleHoleVO> pageInfo = new PageInfo<>(holeMapper.getHoleList());
        return new Pager<>(pageInfo);
    }

    @Override
    public void powerDelHole(Integer id) {
        Boolean bool = holeMapper.powerDelHole(id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }


}
