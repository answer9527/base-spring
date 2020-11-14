package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Letter;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.LetterService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.LetterVO;
import com.answer.base.vo.Pager;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/v1/letter")
@Validated
public class LetterController {
    @Autowired
    private LetterService letterService;

    @PostMapping("/insert")
    @ScopeLevel(0)
    public Msg insertOne(HttpServletRequest request, @RequestBody Letter letter){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        letter.setUid(uid);
        letterService.insertOne(letter);
        return ResultUtil.success("success");
    }

    @GetMapping("/myletter")
    @ScopeLevel(0)
    public Msg getMyLetter(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        Letter letter = letterService.getMyLetter(uid);
        return ResultUtil.success(letter);
    }

//
    @PostMapping("/update")
    @ScopeLevel(0)
    public Msg updateMyLetter(HttpServletRequest request,@RequestBody Letter letter){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        letter.setUid(uid);
        letterService.updateLetter(letter);
        return ResultUtil.success();
    }

    @PostMapping("/list")
    @ScopeLevel(9)
    public Msg getLetterList(@RequestBody PagingDTO pagingDTO){
        Pager<LetterVO> letterList = letterService.getLetterList(pagingDTO);
        return  ResultUtil.success(letterList);
    }

//    获取按月份获取数量
    @GetMapping("/month/planCount")
    public Msg getLetterMonthPlan(@RequestParam @Min(0) Integer y,@RequestParam @Max(value = 12,message = "月份最大不超过12") @Min(value = 0,message = "月份不小于0")  Integer m ){
        return ResultUtil.success("xxx");
    }


}
