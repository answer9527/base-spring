package com.answer.base.service.Impl;

import com.answer.base.dao.UserMapper;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.PwdTokenDTO;
import com.answer.base.dto.UpdateUserDTO;
import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.entity.User;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.exception.http.TokenException;
import com.answer.base.service.UserService;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UserInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
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
    public UserInfoVO selectUserIdByOpenid(String openid) {
        Optional<UserInfoVO> optionalUserInfoVO =Optional.ofNullable(userMapper.selectUserByOpenid(openid));
        UserInfoVO userInfoVO = optionalUserInfoVO.orElseThrow(()->new TokenException(40005));
        return userInfoVO;
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

    @Override
    public Pager<UserInfoVO> selectUserList(PagingDTO pagingDTO) {
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<UserInfoVO> pageInfo = new PageInfo<>(userMapper.selectUserList(pagingDTO));
        Pager<UserInfoVO> pager = new Pager<>(pageInfo);
        return pager;
    }


}
