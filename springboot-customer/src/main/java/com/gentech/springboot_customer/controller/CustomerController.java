package com.gentech.springboot_customer.controller;

import com.gentech.springboot_customer.entity.CustomerEntity;
import com.gentech.springboot_customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CustomerController {
    private CustomerService custService;
    public CustomerController(CustomerService custService){
        super();
        this.custService=custService;
    }
    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers(){
        return custService.getCustomers();
    }
}
