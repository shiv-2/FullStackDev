package com.gentech.springboot_customer.serviceimpl;

import com.gentech.springboot_customer.entity.CustomerEntity;
import com.gentech.springboot_customer.repository.CustomerRepository;
import com.gentech.springboot_customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository custRepo;
    public CustomerServiceImpl(CustomerRepository custRepo){
        super();
        this.custRepo=custRepo;
    }
    @Override
    public List<CustomerEntity> getCustomers(){
        return custRepo.findAll();
    }
}
