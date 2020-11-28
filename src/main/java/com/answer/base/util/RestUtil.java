package com.answer.base.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RestUtil {
     HttpEntity<Map<String, String>> generatePostJson(Map<String, String> jsonMap);
     ResponseEntity<String> postForEntity(String url, HttpEntity<Map<String, String>> postJson,Class c);
     ResponseEntity getForObject(String url,Class T);
}
