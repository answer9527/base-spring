package com.answer.base.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentMsgVO {
    private Integer id;
    private Integer commentId;
    private String content;
    private String title;
    private Integer hid;
    private String userName;
    private Integer type;
    private Boolean isRoot;
    private Boolean isRead;
    private Integer holeOrClassicId;
}
