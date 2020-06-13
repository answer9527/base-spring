package com.answer.base.api.v1;

import com.answer.base.dto.UserRegisterDTO;
import com.answer.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Integer registerUserByAccount(@RequestBody UserRegisterDTO userRegisterDTO){
        return userService.registerByAccount(userRegisterDTO);
    }
}
