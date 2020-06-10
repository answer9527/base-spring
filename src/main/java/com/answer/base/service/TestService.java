package com.answer.base.service;

import com.answer.base.entity.User;

public interface TestService {
    User searchUser(int id);
    int insertUser(User user);
}
