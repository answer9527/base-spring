package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Version extends BaseEntity {
    private Integer id;
    private String intro;
    @NotNull
    private String release;
    @NotNull
    private String wgt;

    public String getWgt() {
        Boolean bool = wgt.matches("http(.*)");
        if(bool){
            return wgt;
        }else{
            return _domain()+wgt;
        }
    }
}
