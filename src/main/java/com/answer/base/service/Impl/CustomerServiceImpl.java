package com.answer.base.service.Impl;

import com.answer.base.dao.CustomerMapper;
import com.answer.base.entity.Customer;
import com.answer.base.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Customer getCustomer(int id) {
        return customerMapper.getCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomer(Integer limit) {
        return customerMapper.getAllCount(limit);
    }
}
