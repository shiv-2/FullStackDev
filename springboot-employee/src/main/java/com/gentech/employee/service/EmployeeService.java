package com.gentech.employee.service;

import com.gentech.employee.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeEntity> getAllEmployee();
    String addEmployee(EmployeeEntity employeeEntity);
    EmployeeEntity getEmployeeById(int id) throws Exception;
    String updateEmployee(EmployeeEntity employeeEntity,int id);
    String deleteEmployee(int id);
}
