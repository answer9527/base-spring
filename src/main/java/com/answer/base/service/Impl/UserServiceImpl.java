package com.answer.base.service.Impl;

import com.answer.base.dao.UserMapper;
import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer registerByAccount(UserRegisterDTO userRegisterDTO) {
        return userMapper.registerUser(userRegisterDTO);
    }
}
