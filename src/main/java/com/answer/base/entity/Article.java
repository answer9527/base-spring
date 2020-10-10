package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Article extends BaseEntity {
    private Integer id;
    private String title;
    private String intro;
    private String content;
    private Integer uid;
    private String userName;
    private String avatarUrl;

    public String getAvatarUrl() {
        Boolean bool = avatarUrl.matches("http(.*)");
        if(bool){
            return avatarUrl;
        }else{
            return _domain()+avatarUrl;
        }
    }
}
