package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Letter;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.LetterService;
import com.answer.base.util.IMailService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.LetterVO;
import com.answer.base.vo.MonthCountVO;
import com.answer.base.vo.Pager;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/v1/letter")
@Validated
public class LetterController {
    @Autowired
    private LetterService letterService;
    @Autowired
    private IMailService iMailService;
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

//    获取按年月月份获取数量
    @GetMapping("/month/planCount")
    @ScopeLevel(9)
    public Msg getLetterMonthPlan(@RequestParam @Min(0) Integer y,@RequestParam @Max(value = 12,message = "月份最大不超过12") @Min(value = 0,message = "月份不小于0")  Integer m ){
        List<MonthCountVO> countVOList = letterService.getLetterMonthPlan(y,m);
        return ResultUtil.success(countVOList);
    }

//    按年月日获取letter列表
    @PostMapping("/byTime")
    @ScopeLevel(9)
    public Msg getLetterListByTime(@RequestParam @Min(0) Integer y,@RequestParam @Max(12) @Min(0)  Integer m, @RequestParam @Max(31) Integer d,@RequestBody PagingDTO pagingDTO){
        Pager<LetterVO> letterVOPager = letterService.getLetterListByTime(y,m,d,pagingDTO);
        return ResultUtil.success(letterVOPager);
    }

    @PostMapping("/send")
    @ScopeLevel(9)
    public Msg sendLetter(@RequestParam Integer id){

//        iMailService.sendSimpleMail("568427129@qq.com","测试","6666");
//        iMailService.sendHtmlMail("568427129@qq.com","测试","<img class=\"normal\" width=\"430px\" data-loadfunc=\"0\" src=\"https://pics4.baidu.com/feed/71cf3bc79f3df8dc13ff1b7405b0e58c451028ba.jpeg?token=5ef85bc821931c09a1fe58d0cb33a4c2\" data-loaded=\"0\">");
//        iMailService.sendFileMail("568427129@qq.com","测试","<h1>给自己的信</h1>","pom.xml");
        return ResultUtil.success("xxx");
    }

}
