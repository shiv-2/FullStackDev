package com.gentech.springboot_customer.repository;

import com.gentech.springboot_customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

}
