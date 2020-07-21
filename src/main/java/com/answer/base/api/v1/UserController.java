package com.answer.base.api.v1;

import com.answer.base.dto.PwdTokenDTO;
import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.dto.WxTokenDTO;
import com.answer.base.entity.User;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.exception.http.TokenException;
import com.answer.base.service.UserService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
//   账号密码形式 用户注册
    @PostMapping("/register")
    public Msg registerUserByAccount(@RequestBody @Validated UserRegisterDTO userRegisterDTO){
//        Integer uid = userService.selectUidByAccount(userRegisterDTO.getAccount());
//        if(uid==null){
//            return userService.register(userRegisterDTO);
//        }else{
//            throw new ParameterException(20001);
//        }
        userService.register(userRegisterDTO);
        return ResultUtil.success("注册成功");

    }


//   用户微信新注册后登录
    @PostMapping("/registerToken")
    public Msg getTokenByWxRegister(@RequestBody  WxTokenDTO wxTokenDTO){
        String openid = userService.code2session(wxTokenDTO.getCode());
//        User user = userService.selectUserIdByOpenid(openid);
        Optional<Integer> uidOptional = Optional.ofNullable(userService.selectUidByOpenid(openid));
        Integer uid;
        if(uidOptional.isPresent()){
            uid = uidOptional.get();
        }else{
            UserRegisterDTO userRegisterDTO =new UserRegisterDTO();
            userRegisterDTO.setOpenid(openid);
            userRegisterDTO.setUserName(wxTokenDTO.getNickName());
            userRegisterDTO.setAvatarUrl(wxTokenDTO.getAvatarUrl());
            userRegisterDTO.setGender(wxTokenDTO.getGender());
            userService.register(userRegisterDTO);
            uid = userRegisterDTO.getId();
        }

        String token = JwtToken.makeToken(uid);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("uid",Integer.toString(uid));
        return ResultUtil.success(tokenMap,"登录成功");
    }

//    用户的账号密码登录
    @PostMapping("/pwdToken")
    public Msg getTokenByPwd(@RequestBody @Validated PwdTokenDTO pwdTokenDTO){
        Optional<Integer> uidOptional = Optional.ofNullable(userService.selectUidByAccPwd(pwdTokenDTO));
        Integer uid = uidOptional.orElseThrow(()->new TokenException(20002));
        String token = JwtToken.makeToken(uid);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("uid",Integer.toString(uid));
        return ResultUtil.success(tokenMap,"登陆成功");
    }

//    已注册的用户使用微信code直接登录
    @GetMapping("/codeLogin")
    public Msg getTokenByCode(@RequestParam String code){
        String openid = userService.code2session(code);
        Optional<Integer> uidOptional = Optional.ofNullable(userService.selectUidByOpenid(openid));
        if(uidOptional.isPresent()){
            Integer uid = uidOptional.get();
            String token = JwtToken.makeToken(uid);
            Map<String,String> tokenMap = new HashMap<>();
            tokenMap.put("token",token);
            tokenMap.put("uid",Integer.toString(uid));
            return ResultUtil.success(tokenMap,"登陆成功");
        }else{
            throw new TokenException(40005);
        }
    }




}
