package com.answer.base.service.Impl;

import com.answer.base.dao.WxMapper;
import com.answer.base.service.WxService;
import com.answer.base.util.FetchUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class WxServiceImpl implements WxService {
    @Autowired
    private WxMapper wxMapper;
    @Value("${wx.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${wx.templateListUrl}")
    private String templateListUrl;
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public String getAccessToken() {
        Optional<String> stringOptional = Optional.ofNullable(wxMapper.getAccessToken());
        String accessToken;
        if(stringOptional.isPresent()){
            accessToken = stringOptional.get();
        }else{
            String url = MessageFormat.format(this.accessTokenUrl,this.appid,this.appsecret);
            FetchUtil<Map> fetchUtil = new FetchUtil();
            ResponseEntity<Map> mapResponseEntity = fetchUtil.getForObject(url,Map.class);
            Map map1 = mapResponseEntity.getBody();
            accessToken = (String) map1.get("access_token");
            this.insertAccessToken(accessToken);
        }
        return accessToken;
    }

    @Override
    public void insertAccessToken(String token) {
        wxMapper.insertAccessToken(token);
    }

    @Override
    public ArrayList getTemplateList() {
        String access_token = this.getAccessToken();
        String url = MessageFormat.format(this.templateListUrl,access_token);
        FetchUtil<Map> fetchUtil = new FetchUtil();
        ResponseEntity<Map> mapResponseEntity = fetchUtil.getForObject(url,Map.class);
        ArrayList tempList = (ArrayList) mapResponseEntity.getBody().get("data");
        return tempList;
    }
}
