package com.answer.base.vo;

import com.answer.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CommentMsgVO extends BaseEntity {
    private Date createTime;
    private Integer id;
    private Integer commentId;
    private Integer commentUid;
    private Integer commentPid;
    private String content;
    private String title;
    private String userName;
    private String avatarUrl;
    private Integer type;
    private Boolean isRoot;
    private Boolean isRead;
    private Integer holeOrClassicId;

    public String getAvatarUrl() {
        Boolean bool = avatarUrl.matches("http(.*)");
        if(bool){
            return avatarUrl;
        }else{
            return _domain()+avatarUrl;
        }
    }
}
