package com.answer.base.dao;

import com.answer.base.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {
    Customer getCustomer(int id);
//    获取全部
    List<Customer> getAllCount(int limit);
}
