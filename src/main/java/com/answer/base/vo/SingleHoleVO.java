package com.answer.base.vo;

import com.answer.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SingleHoleVO extends BaseEntity {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private String userName;
    private Integer gender;
    private String avatarUrl;
    private Integer type;
    private Integer canComment;
    private Integer commentVisible;
    private Date createTime;

    public String getAvatarUrl() {
        Boolean bool = avatarUrl.matches("http(.*)");
        if(bool){
            return avatarUrl;
        }else{
            return _domain()+avatarUrl;
        }
    }

}
