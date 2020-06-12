package com.answer.base.api.v1;

import com.answer.base.dao.UserMapper;
import com.answer.base.dto.UserDTO;
import com.answer.base.entity.User;
import com.answer.base.exception.http.NotFoundException;
import com.answer.base.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/v1/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/get")
    public User getUser(@RequestParam int id){
        User user= testService.searchUser(id);
        return user;
    }

    @PostMapping("/insert")
    public int insertUser(@RequestBody @Validated UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        testService.insertUser(user);
        return 111;
    }

    @GetMapping("/error")
    public String error(){
        throw new NotFoundException(10001);
    }


    @GetMapping("/fail/{id}")
    public void fail(@PathVariable @Max(1)  Long id){
        System.out.println(222);
    }
    @GetMapping("/fail2")
    public void fail2(@RequestParam(defaultValue = "1") @Max(10) Integer id,@RequestParam @Min(1) Integer age){

    }

}