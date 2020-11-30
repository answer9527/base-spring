package com.answer.base.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class FetchUtil<T> {

    public HttpEntity<Map<String, String>> generatePostJson(Map<String, String> jsonMap) {
        //如果需要其它的请求头信息、都可以在这里追加
        HttpHeaders httpHeaders = new HttpHeaders();

        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");

        httpHeaders.setContentType(type);

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(jsonMap, httpHeaders);

        return httpEntity;
    }

    public ResponseEntity<T> postForEntity(String url,Map data,Class T){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url,generatePostJson(data),T);
        return responseEntity;
    }

    public ResponseEntity<T> getForObject(String url,Class T){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url,T);
    }

}
