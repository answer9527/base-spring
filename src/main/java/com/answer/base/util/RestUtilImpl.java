package com.answer.base.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

// 后台请求相关的
@Service
public class RestUtilImpl implements RestUtil {
    @Override
    public HttpEntity<Map<String, String>> generatePostJson(Map<String, String> jsonMap) {
       //如果需要其它的请求头信息、都可以在这里追加
        HttpHeaders httpHeaders = new HttpHeaders();

        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");

        httpHeaders.setContentType(type);

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(jsonMap, httpHeaders);

        return httpEntity;
    }

    @Override
    public ResponseEntity<String> postForEntity(String url, HttpEntity<Map<String, String>> postJson, Class c) {
        return null;
    }

    @Override
    public ResponseEntity<String> getForObject(String url, Class t) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url,t);
        return null;
    }
}
