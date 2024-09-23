package com.gentech.springboot_customer.service;


import com.gentech.springboot_customer.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getCustomers();
    CustomerEntity saveCustomers(CustomerEntity customerEntity);
    CustomerEntity getCustomersById(long id);
    CustomerEntity updateCustomer(CustomerEntity customerEntity);
    String deleteCustomer(long id);
}
