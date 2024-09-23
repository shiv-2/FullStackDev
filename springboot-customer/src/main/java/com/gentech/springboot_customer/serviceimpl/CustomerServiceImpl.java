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

    @Override
    public CustomerEntity saveCustomers(CustomerEntity customerEntity) {
        return custRepo.save(customerEntity);
    }

    @Override
    public CustomerEntity getCustomersById(long id) {
        return custRepo.findById(id).orElse(null);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        CustomerEntity customer = custRepo.findById(customerEntity.getCustomer_id()).orElse(null);
        if(customer!=null){
            customer.setCustomer_name(customerEntity.getCustomer_name());
            customer.setCustomer_loc(customerEntity.getCustomer_loc());
            customer.setCustomer_phone(customerEntity.getCustomer_phone());
            custRepo.save(customer);
            return customer;
        }
        return null;
    }

    @Override
    public String deleteCustomer(long id) {
        if(custRepo.existsById(id)){
            custRepo.deleteById(id);
            return "The given id "+id+" is successfully deleted";
        }
        return "The given id is not present";
    }
}
