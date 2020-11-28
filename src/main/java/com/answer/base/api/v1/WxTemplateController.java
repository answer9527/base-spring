package com.answer.base.api.v1;

import com.answer.base.service.UserService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/wx/msg")
public class WxTemplateController {
    @Value("${wx.templateListUrl}")
    private String templateListUrl;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;
    @GetMapping("/template/list")
    public Msg getWxMsgTemplateList(){
        RestTemplate restTemplate = new RestTemplate();
        String access_token = userService.getAccessToken();
        String res_text = restTemplate.getForObject(templateListUrl,String.class,access_token);
        Map<String,Object> res_map = new HashMap<>();
        try {
            res_map= mapper.readValue(res_text, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResultUtil.success(res_map.get("data"));
    }
}
