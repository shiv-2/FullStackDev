package com.gentech.springboot_customer.controller;

import com.gentech.springboot_customer.entity.CustomerEntity;
import com.gentech.springboot_customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CustomerController {
    private CustomerService custService;
    public CustomerController(CustomerService custService){
        super();
        this.custService=custService;
    }
    //http://localhost:8082/v1/api/customers
    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers(){
        return custService.getCustomers();
    }
    //http://localhost:8082/v1/api/addcustomer
    @PostMapping("/addcustomer")
    public CustomerEntity saveCustomerDetails(@RequestBody CustomerEntity customerEntity){
        return custService.saveCustomers(customerEntity);
    }
    //http://localhost:8082/v1/api/getCustomerById
    @GetMapping("/getCustomerById/{id}")
    public CustomerEntity getCustomerById(@PathVariable long id){
        return custService.getCustomersById(id);
    }
    //http://localhost:8082/v1/api/updateCustomer
    @PutMapping("/updateCustomer")
    public CustomerEntity updateCustomerDetails(@RequestBody CustomerEntity customerEntity){
        return custService.updateCustomer(customerEntity);
    }
    //http://localhost:8082/v1/api/deleteCustomer
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomerById(@PathVariable long id){
        return custService.deleteCustomer(id);
    }
}
