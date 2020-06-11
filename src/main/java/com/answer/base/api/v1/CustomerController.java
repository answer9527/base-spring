package com.answer.base.api.v1;

import com.answer.base.entity.Customer;
import com.answer.base.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAll")
    public List<Customer> getAllCustomer(@RequestParam Integer limit){
        return customerService.getAllCustomer(limit);
    }

    @PostMapping("/register")
    public String registerByAccount(@RequestBody Customer customer){

        return "xxxx";
    }


}
