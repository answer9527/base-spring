package com.answer.base.service;

import com.answer.base.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(int id);
    List<Customer> getAllCustomer(Integer limit);
}
