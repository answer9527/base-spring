package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class HoleComment extends BaseEntity {
    private Integer id;
    private Integer hid;
    private Integer uid;
    private String userName;
    private String userAvatar;
    private Integer uid_r;
    private String userName_r;
    private String avatarUrl_r;
    private Integer pid;
    private String content;
    private Date createTime;
    private List<HoleComment> child;

    public String getUserAvatar() {
        Boolean bool = userAvatar.matches("http(.*)");
        if(bool){
            return userAvatar;
        }else{
            return _domain()+userAvatar;
        }
    }

}
