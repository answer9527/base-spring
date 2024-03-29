package com.answer.base.vo;

import com.answer.base.entity.BaseEntity;
import com.answer.base.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInfoVO extends BaseEntity {
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private Integer id;
    private String userName;
    private String avatarUrl;
    private Integer gender;
    private String account;
    private Integer scope;

    public String getAvatarUrl() {
        Boolean bool = avatarUrl.matches("http(.*)");
        if(bool){
            return avatarUrl;
        }else{
            return _domain()+avatarUrl;
        }
    }
}
