package com.answer.base.service.Impl;

import com.answer.base.dao.UserMapper;
import com.answer.base.entity.User;
import com.answer.base.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User searchUser(int id) {
        return userMapper.selectUser(id);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
