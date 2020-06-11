package com.answer.base.api.v1;

import com.answer.base.entity.Customer;
import com.answer.base.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        Customer customer = customerService.getCustomer(id);
        return customer;
    }
}
