package com.answer.base.dao;

import com.answer.base.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerMapper {
    Customer getCustomer(int id);
}
