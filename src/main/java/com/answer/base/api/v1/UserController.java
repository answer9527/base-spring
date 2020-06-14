package com.answer.base.api.v1;

import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.dto.WxTokenDTO;
import com.answer.base.entity.User;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.UserService;
import com.answer.base.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
//    用户注册
    @PostMapping("/register")
    public Integer registerUserByAccount(@RequestBody UserRegisterDTO userRegisterDTO){
//        Integer uid = userService.selectUidByAccount(userRegisterDTO.getAccount());
//        if(uid==null){
//            return userService.register(userRegisterDTO);
//        }else{
//            throw new ParameterException(20001);
//        }

        return userService.register(userRegisterDTO);

    }
//   用户登录

    @PostMapping("/wxToken")
    public String getToken(@RequestBody WxTokenDTO wxTokenDTO){
        String openid = userService.code2session(wxTokenDTO.getCode());
//        User user = userService.selectUserIdByOpenid(openid);
        Integer uid = userService.selectUidByOpenid(openid);
        if(uid==null){
            UserRegisterDTO userRegisterDTO =new UserRegisterDTO();
            userRegisterDTO.setOpenid(openid);
            userRegisterDTO.setUserName(wxTokenDTO.getNickName());
            userRegisterDTO.setAvatarUrl(wxTokenDTO.getAvatarUrl());
            userRegisterDTO.setGender(wxTokenDTO.getGender());
            userService.register(userRegisterDTO);
            uid = userRegisterDTO.getId();
        }
        System.out.println(uid);
        String token = JwtToken.makeToken(uid);
        return token;
    }
}
