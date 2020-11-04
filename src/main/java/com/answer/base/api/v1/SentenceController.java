package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Sentence;
import com.answer.base.service.SentenceService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/sentence")
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;
    @GetMapping("/rand")
    public Msg getRandSentence(){
        Sentence sentence = sentenceService.getRandSentence();
        return ResultUtil.success(sentence);
    }

    @PostMapping("/list")
    public Msg getSentence(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        Pager<Sentence> sentenceList = sentenceService.getSentenceList(pagingDTO);
        return ResultUtil.success(sentenceList);
    }

    @PostMapping("/insert")
    public Msg insertSentence(HttpServletRequest request,@RequestBody Sentence sentence){
        String token  = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        sentenceService.insertSentence(sentence);
        return  ResultUtil.success("新增成功");
    }

    @GetMapping("/del/{id}")
    public Msg delSentence(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        sentenceService.delSentence(id);
        return ResultUtil.success("删除成功！");
    }
}
