package com.answer.base.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface WxService {
//    获取 accessToken
    String getAccessToken();
//    数据库插入一条accessToken
    void insertAccessToken(String token);
//    获取 微信推送消息模板列表
    ArrayList getTemplateList();
}
