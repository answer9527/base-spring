package com.answer.base.service.Impl;

import com.answer.base.dao.HoleMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Hole;
import com.answer.base.service.HoleService;
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
}
