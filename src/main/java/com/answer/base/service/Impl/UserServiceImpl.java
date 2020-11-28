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
import com.answer.base.util.RestUtil;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UserInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    @Value("${wx.accessTokenUrl}")
    private String accessTokenUrl;
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

    @Override
    public String getAccessToken() {
        String url = MessageFormat.format(this.accessTokenUrl,this.appid,this.appsecret);
        RestTemplate rest = new RestTemplate();
        String accessString = rest.getForObject(url,String.class);
        Map<String,Object> accessToken = new HashMap<>();
        try {
            accessToken= mapper.readValue(accessString,Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String tokenString = (String) accessToken.get("access_token");
        return tokenString;
    }

    @Override
    public void sendTemplateMsg(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+accessToken;
        RestTemplate rest = new RestTemplate();


        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        Map map5 = new HashMap();
        map1.put("value","1111");
        map2.put("value","2020/11/27 17:00:00");
        map3.put("value","Aslan");
        map4.put("value","4444");
        map5.put("value","5555");

        Map mapY = new HashMap();
        mapY.put("thing1",map1);
        mapY.put("time2",map2);
        mapY.put("name3",map3);
        mapY.put("thing5",map4);
        mapY.put("thing6",map5);

//        Map mapX = new HashMap();
//        mapX.put("template_id","2hbbRktn9dBnQ6Sni4l3cQgzkJvtgcVUoSy7psVCxPQ");
//        mapX.put("page","pages/writeLetter/index");
//        mapX.put("data",mapY);
//        mapX.put("emphasis_keyword","test");


//        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
//        request.add("access_token",accessToken);
//        request.add("touser","oXj-L5V1I110yGhhDyCCpZfKKFcQ");
//        request.add("weapp_template_msg",mapX);
        Map request = new HashMap();
        request.put("access_token",accessToken);
        request.put("touser","oXj-L5f181gCoZndLA_JIVWLGicg");
//        request.put("weapp_template_msg",mapX);
        request.put("template_id","2hbbRktn9dBnQ6Sni4l3cQgzkJvtgcVUoSy7psVCxPQ");
        request.put("data",mapY);
        request.put("page","pages/letterDetail/index?id=132");
//        String res = rest.postForObject(url,request,String.class);
//        String res = RestUtil.generatePostJson(request)
        ResponseEntity<String> apiResponse = rest.postForEntity(url,RestUtil.generatePostJson(request),String.class);

    }



}
