package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.entity.Letter;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.LetterService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/letter")
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
        letterService.updateLetter(letter);
        return ResultUtil.success("222");
//        if(uid == letter.getUid()){
//
//
//        }else{
//            throw new ParameterException(40004);
//        }

    }


}
