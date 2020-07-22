package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
public class Classic extends BaseEntity {

    private Integer id;
    private Date recommend_time;
    private Boolean is_recommend=false;
    private String title;
    private String intro;
    private String sub_title;
    private String content;
    private Integer type;
    private String url;
    private String author;
    private String image;
    private Integer like_count;
    private Boolean like_status=false;

    public String getImage() {
        String base_file = "https://mina.huzhihua.cn";
        return base_file+image;
    }
}
