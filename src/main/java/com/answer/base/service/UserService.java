package com.answer.base.service;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.PwdTokenDTO;
import com.answer.base.dto.UpdateUserDTO;
import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.entity.User;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UserInfoVO;

import java.util.List;

public interface UserService {
//  注册用户
    Integer register(UserRegisterDTO userRegisterDTO);
//    微信code 换取openid
    String code2session(String code);
//    根据openid查询用户
    User selectUserIdByOpenid(String openid);
//    根据openid查询uid
    Integer selectUidByOpenid(String openid);

//    根据account查询uid
    Integer selectUidByAccount(String account);

//    根据account和password  验证登录
    Integer selectUidByAccPwd(PwdTokenDTO pwdTokenDTO);

//    根据uid查找user
    UserInfoVO selectUserByAccPwd(PwdTokenDTO pwdTokenDTO);

//    修改个人资料
    void updateUserSelfInfo(UpdateUserDTO updateUserDTO);

//    获取用户列表
    Pager<UserInfoVO> selectUserList(PagingDTO pagingDTO);


}
