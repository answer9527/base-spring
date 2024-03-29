package com.answer.base.api.v1;

import com.answer.base.dto.UserDTO;
import com.answer.base.entity.User;
import com.answer.base.exception.http.NotFoundException;
import com.answer.base.service.TestService;
import com.answer.base.util.FetchUtil;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/v1/test")
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/get")
    public Msg getUser(@RequestParam int id){
//        User user= testService.searchUser(id);
        return ResultUtil.success("测试成功-0 ");
    }

    @GetMapping("/calendar")
    public Msg getCalendar(){
        int [] counts = new int[31];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = (int) (Math.random()*2);
        }
        Map map = new HashMap();
        map.put("counts",counts);
        return ResultUtil.success(map);
    }

    @PostMapping("/insert")
    public int insertUser(@RequestBody @Validated UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        testService.insertUser(user);
        return 111;
    }

    @GetMapping("/error")
    public String error(){
        throw new NotFoundException(10001);
    }


    @GetMapping("/fail/{id}")
    public void fail(@PathVariable @Max(1)  Long id){
        System.out.println(222);
    }


    @GetMapping("/fail2")
    public void fail2(@RequestParam(defaultValue = "1") @Max(10) Integer id,@RequestParam @Min(1) Integer age){

    }

    @GetMapping("/scope")
    public Msg getScope(){
        Map map = new HashMap();
        map.put("scope",true);
        return ResultUtil.success(map);
    }

    @Value("${wx.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;
    @GetMapping("/rest")
    public Msg testRest(){
        String url = MessageFormat.format(this.accessTokenUrl,this.appid,this.appsecret);
        FetchUtil<Map> fetchUtil = new FetchUtil();

       ResponseEntity<Map> map = fetchUtil.getForObject(url,Map.class);
       Map map1 = map.getBody();
       String access_token = (String) map1.get("access_token");
        return  ResultUtil.success(map.getBody());
    }


}
