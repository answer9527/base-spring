package com.answer.base.service.Impl;

import com.answer.base.dao.ArticleMapper;
import com.answer.base.entity.Article;
import com.answer.base.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

//    @Override
//    public Article getDetail(Integer id) {
//        return articleMapper.getArticleDetail(id);
//    }
}
