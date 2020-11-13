package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.service.OtherService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.SortTotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/other")
public class OtherController {
    @Autowired
    private OtherService otherService;

    @GetMapping("/sort/all")
//    @ScopeLevel(9)
    public Msg getDashboardSortTotal(){
        List<SortTotalVO> sortTotalVOList = otherService.getDashboardSortTotal();
        return ResultUtil.success(sortTotalVOList);
    }

}
