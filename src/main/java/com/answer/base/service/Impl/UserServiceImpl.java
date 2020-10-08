package com.answer.base.service.Impl;

import com.answer.base.dao.UserMapper;
import com.answer.base.dto.PwdTokenDTO;
import com.answer.base.dto.UpdateUserDTO;
import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.entity.User;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.UserService;
import com.answer.base.vo.UserInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ObjectMapper mapper;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.code2session}")
    private String code2sessionUrl;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer register(UserRegisterDTO userRegisterDTO) {
        Optional<Integer> uidOptional =  Optional.ofNullable(this.selectUidByAccount(userRegisterDTO.getAccount()));
        if(uidOptional.isPresent()){
            throw new ParameterException(20001);
        }else{
            return userMapper.registerUser(userRegisterDTO);
        }
    }

    @Override
    public String code2session(String code) {
        String url = MessageFormat.format(this.code2sessionUrl,this.appid,this.appsecret,code);
        RestTemplate rest = new RestTemplate();
        String sessionText = rest.getForObject(url,String.class);
        Map<String,Object> session = new HashMap<>();
        try {
            session= mapper.readValue(sessionText,Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String openid = (String) session.get("openid");
        return openid;
    }

    @Override
    public User selectUserIdByOpenid(String openid) {
        User user = userMapper.selectUserByOpenid(openid);
        return user;
    }

    @Override
    public Integer selectUidByOpenid(String openid) {
        return userMapper.selectUidByOpenid(openid);
    }

    @Override
    public Integer selectUidByAccount(String account) {
        return userMapper.selectUidByAccount(account);
    }

    @Override
    public Integer selectUidByAccPwd(PwdTokenDTO pwdTokenDTO) {
        return userMapper.selectUidByAccPwd(pwdTokenDTO);
    }

    @Override
    public UserInfoVO selectUserByAccPwd(PwdTokenDTO pwdTokenDTO) {
        return userMapper.selectUserByAccPwd(pwdTokenDTO);
    }

    @Override
    public void updateUserSelfInfo(UpdateUserDTO updateUserDTO) {
        Boolean bool = userMapper.updateUserSelfInfo(updateUserDTO);
        if(!bool){
            throw new ParameterException(50003);
        }
    }


}
