package com.answer.base.api.v1;

import com.answer.base.service.UserService;
import com.answer.base.service.WxService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/wx")
public class WxTemplateController {
    @Autowired
    private WxService wxService;
    @GetMapping("/msg/template/list")
    public Msg getWxMsgTemplateList(){
//        RestTemplate restTemplate = new RestTemplate();
//        String access_token = wxService.getAccessToken();
        ArrayList templateList = wxService.getTemplateList();
        return ResultUtil.success(templateList);
    }

}
