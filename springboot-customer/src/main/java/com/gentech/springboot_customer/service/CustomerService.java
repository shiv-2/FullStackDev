package com.gentech.springboot_customer.service;

import com.gentech.springboot_customer.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getCustomers();
}
