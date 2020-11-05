package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
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
    @ScopeLevel(9)
    public Msg getSentence(@RequestBody PagingDTO pagingDTO){
        Pager<Sentence> sentenceList = sentenceService.getSentenceList(pagingDTO);
        return ResultUtil.success(sentenceList);
    }

    @PostMapping("/insert")
    @ScopeLevel(9)
    public Msg insertSentence(@RequestBody Sentence sentence){
        sentenceService.insertSentence(sentence);
        return  ResultUtil.success("新增成功");
    }

    @GetMapping("/del/{id}")
    @ScopeLevel(9)
    public Msg delSentence(@PathVariable Integer id){
        sentenceService.delSentence(id);
        return ResultUtil.success("删除成功！");
    }
}
