package com.answer.base.dao;

import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //    根据id查询
    User selectUser(int id);
    //    新增测试
    int insertUser(User user);



//    注册用户
    int registerUser(UserRegisterDTO userRegisterDTO);

//    根据openid 查询用户
    User selectUserByOpenid(String openid);
//  根据openid查询用户的id
    Integer selectUidByOpenid(String openid);
//    根据account查询用户的id
    Integer selectUidByAccount(String account);
}
