package com.answer.base.dao;

import com.answer.base.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //    根据id查询
    User selectUser(int id);

    //    新增
    int insertUser(User user);

    //    带条件的新增
    int insertSelective(User user);

    int updateUser(User user);
}
