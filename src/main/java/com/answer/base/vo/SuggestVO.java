package com.answer.base.vo;

import com.answer.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestVO extends BaseEntity {
    private String userName;
    private String avatarUrl;
    private Integer id;
    private String suggestInfo;
    private String replyInfo;
    public String getAvatarUrl() {
        Boolean bool = avatarUrl.matches("http(.*)");
        if(bool){
            return avatarUrl;
        }else{
            return _domain()+avatarUrl;
        }
    }
}
