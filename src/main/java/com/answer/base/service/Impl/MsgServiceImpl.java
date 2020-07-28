package com.answer.base.service.Impl;

import com.answer.base.dao.MsgMapper;
import com.answer.base.entity.Msg;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgMapper msgMapper;
    @Override
    public void insertMsg(Msg msg) {
        Boolean bool = msgMapper.insertMsg(msg);
        if(!bool){
            throw new ParameterException(10001);
        }
    }
}
