package com.answer.base.service;

import com.answer.base.dto.UserRegisterDTO;

public interface UserService {
    Integer registerByAccount(UserRegisterDTO userRegisterDTO);
}
