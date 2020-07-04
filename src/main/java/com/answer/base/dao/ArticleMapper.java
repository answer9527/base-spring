package com.answer.base.dao;

import com.answer.base.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleMapper {
    Article getArticleDetail(Integer id);
}
