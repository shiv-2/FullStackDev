package com.gentech.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employee_id;
    @Column(name = "employee_name")
    private String employee_name;
    @Column(name = "employee_mail")
    private String employee_mail;
    @Column(name = "employee_dept")
    private String employee_dept;
    @Column(name = "employee_phone")
    private String employee_phone;
    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at",nullable=false)
    private Date updatedAt;
}
