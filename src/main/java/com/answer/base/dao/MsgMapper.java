package com.answer.base.dao;

import com.answer.base.entity.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MsgMapper {
    Boolean insertMsg(Msg msg);
}
