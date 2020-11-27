package com.answer.base.api.v1;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/wx")
public class WxController {
    @Value("${wx.token}")
    private String token;
    @Value("${wx.appid2}")
    private String appid;
    @Value("${wx.appsecret2}")
    private String appsecret;

    @GetMapping("/token")
    private String test(@RequestParam("signature") String signature,@RequestParam("timestamp") String timestamp,@RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appsecret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
//        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder().toString()
        return echostr;
    }
}
