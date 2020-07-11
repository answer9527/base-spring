package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Hole;

import java.util.List;

public interface HoleService {
    void insertHole(Hole hole);
    List<Hole> getMyHole(PagingDTO pagingDTO);
}
