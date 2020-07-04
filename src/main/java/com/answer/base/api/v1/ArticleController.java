package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.entity.Article;
import com.answer.base.service.ArticleService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/detail/{id}")
//    @ScopeLevel(value = 0)
    public Msg getArticleDetail(@PathVariable Integer id){
       Article article= articleService.getDetail(id);
        return ResultUtil.success(article);
    }
}
