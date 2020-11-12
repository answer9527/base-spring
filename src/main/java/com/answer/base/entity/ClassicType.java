package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;


/**
        create table `classic_type`(
                `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT "主键id",
                `name` VARCHAR(20) NOT NULL COMMENT "类型名",
                `remark` VARCHAR(200) COMMENT "备注",
                `create_time` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT "创建时间",
                `update_time` TIMESTAMP COMMENT "更新时间",
                `delete_time` TIMESTAMP COMMENT "删除时间",
                UNIQUE unq_name(name),
                INDEX idx_name(name)
        )COMMENT "classic类型表"
 **/
@Getter
@Setter
public class ClassicType  extends BaseEntity{
    private Integer id;
    private String name;
    private String remark;
}
