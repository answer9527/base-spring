package com.answer.base.api.v1;

import com.answer.base.entity.Sentence;
import com.answer.base.service.SentenceService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
