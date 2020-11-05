package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.ReplySuggestDTO;
import com.answer.base.entity.Suggest;
import com.answer.base.service.SuggestService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1/suggest")
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    @PostMapping("/insert")
    @ScopeLevel(0)
    public Msg insertSuggest(HttpServletRequest request, @RequestBody Suggest suggest){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        suggest.setUid(uid);
        suggestService.insertSuggest(suggest);
        return ResultUtil.success("反馈成功！");
    }

    @PostMapping("/reply")
    @ScopeLevel(9)
    public Msg replySuggest(@RequestBody @Validated ReplySuggestDTO replySuggestDTO){
        suggestService.replySuggest(replySuggestDTO);
        return ResultUtil.success("回复成功！");
    }

    @PostMapping("/all")
    public Msg getAllSuggest(@RequestBody PagingDTO pagingDTO){
        return ResultUtil.success(suggestService.getAll(pagingDTO));
    }

    @GetMapping("/del/{id}")
    @ScopeLevel(9)
    public Msg delSuggest(@PathVariable Integer id){
        suggestService.delSuggest(id);
        return ResultUtil.success("删除成功！");
    }

}
