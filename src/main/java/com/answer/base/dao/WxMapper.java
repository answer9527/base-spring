package com.answer.base.dao;

import com.answer.base.entity.AccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WxMapper {
    String getAccessToken();
    Boolean insertAccessToken(String token);
}
