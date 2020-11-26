package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.*;
import com.answer.base.entity.User;
import com.answer.base.exception.http.TokenException;
import com.answer.base.service.UserService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.LoginResultVO;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UserInfoVO;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        Optional<UserInfoVO> userOptional = Optional.ofNullable(userService.selectUserByAccPwd(pwdTokenDTO));
        UserInfoVO userInfoVO = userOptional.orElseThrow(()->new TokenException(40007));
        String token = JwtToken.makeToken(userInfoVO.getId());
        LoginResultVO loginResultVO = LoginResultVO.builder().token(token).uid(userInfoVO.getId()).userInfo(userInfoVO).build();
        return ResultUtil.success(loginResultVO,"登陆成功");
    }

//    管理员登录
    @PostMapping("/pwdToken/admin")
    public Msg getAdminTokenByPwd(@RequestBody @Validated PwdTokenDTO pwdTokenDTO){
        Optional<UserInfoVO> userOptional = Optional.ofNullable(userService.selectUserByAccPwd(pwdTokenDTO));
        UserInfoVO userInfoVO = userOptional.orElseThrow(()->new TokenException(40007));
        String token = JwtToken.makeToken(userInfoVO.getId(),userInfoVO.getScope());
        LoginResultVO loginResultVO = LoginResultVO.builder().token(token).uid(userInfoVO.getId()).userInfo(userInfoVO).build();
        return ResultUtil.success(loginResultVO,"登陆成功");
    }

//    已注册的用户使用微信code直接登录
    @GetMapping("/codeLogin")
    public Msg getTokenByCode(@RequestParam String code){
        String openid = userService.code2session(code);
//        Optional<Integer> uidOptional = Optional.ofNullable(userService.selectUidByOpenid(openid));
//        if(uidOptional.isPresent()){
//            Integer uid = uidOptional.get();
//            String token = JwtToken.makeToken(uid);
//            Map<String,String> tokenMap = new HashMap<>();
//            tokenMap.put("token",token);
//            tokenMap.put("uid",Integer.toString(uid));
//            return ResultUtil.success(tokenMap,"登陆成功");
//        }else{
//            throw new TokenException(40005);
//        }
        UserInfoVO userInfoVO = userService.selectUserIdByOpenid(openid);
        LoginResultVO loginResultVO = LoginResultVO.builder().token(JwtToken.makeToken(userInfoVO.getId())).uid(userInfoVO.getId()).userInfo(userInfoVO).build();
        return ResultUtil.success(loginResultVO);
    }

//    更新个人用户信息
    @PostMapping("/update/self")
    public Msg updateMyInfo(HttpServletRequest request, @RequestBody UpdateUserDTO updateUserDTO){
        Integer uid = JwtToken.RequestGetUid(request);
        updateUserDTO.setId(uid);
        userService.updateUserSelfInfo(updateUserDTO);
        return ResultUtil.success("修改成功");
    }

//    分页获取用户列表
    @PostMapping("/list")
    @ScopeLevel(9)
    public Msg getUserList( @RequestBody PagingDTO pagingDTO){
        Pager<UserInfoVO> pager = userService.selectUserList(pagingDTO);
        return ResultUtil.success(pager);
    }





}
