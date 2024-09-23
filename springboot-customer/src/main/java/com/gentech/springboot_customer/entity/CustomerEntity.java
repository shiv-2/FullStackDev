package com.gentech.springboot_customer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;
    @Column(name = "customer_name")
    private String customer_name;
    @Column(name="customer_loc")
    private String customer_loc;
    @Column(name = "customer_phone")
    private String customer_phone;

    public CustomerEntity(){

    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_loc() {
        return customer_loc;
    }

    public void setCustomer_loc(String customer_loc) {
        this.customer_loc = customer_loc;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
}
