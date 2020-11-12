package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.entity.ClassicType;
import com.answer.base.service.ClassicTypeService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.SortTotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/classicType")
public class ClassicTypeController {
    @Autowired
    private ClassicTypeService classicTypeService;
    @GetMapping("/all")
    @ScopeLevel(9)
    public Msg getClassicTypeAll(){
        List<ClassicType> classicTypeList = classicTypeService.getClassicTypeAll();
        return ResultUtil.success(classicTypeList);
    }

    //    获取classic的分类统计
    @GetMapping("/sort/total")
    @ScopeLevel(9)
    public Msg getClassicSortTotal(){
        List<SortTotalVO> sortTotalVOList = classicTypeService.getClassicTypeSortTotal();
        return ResultUtil.success(sortTotalVOList);
    }

}
