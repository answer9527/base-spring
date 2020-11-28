package com.answer.base.vo;

import com.answer.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class LetterVO extends BaseEntity {
    private Integer id;
    private Integer uid;
    private String userName;
    private String avatarUrl;
    private String title;
    private String content;
    private String email;
    private Boolean pending;
    private Integer type;
    private String image;
    private Date postTime;
    private Date planTime;
    public String getAvatarUrl() {
        if(image!=null){
            Boolean bool = avatarUrl.matches("http(.*)");
            if(bool){
                return avatarUrl;
            }else{
                return _domain()+avatarUrl;
            }
        }else{
            return null;
        }

    }

    public String getImage() {
        if(image!=null){
            Boolean bool = image.matches("http(.*)");
            if(bool){
                return image;
            }else{
                return _domain()+image;
            }
        }else{
            return null;
        }
    }
}
