package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.HoleComment;
import com.answer.base.service.HoleCommentService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/hole/comment")
public class HoleCommentController {
    @Autowired
    private HoleCommentService holeCommentService;
    @PostMapping("/selectByHid")
    public Msg getHoleCommentByHid(@RequestBody PagingDTO pagingDTO){
        List<HoleComment> holeCommentList = holeCommentService.getHoleCommentByHid(pagingDTO);
        return ResultUtil.success(holeCommentList);
    }
}
