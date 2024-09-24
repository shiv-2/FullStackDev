package com.gentech.employee.serviceimpl;

import com.gentech.employee.entity.EmployeeEntity;
import com.gentech.employee.repository.EmployeeRepository;
import com.gentech.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public String addEmployee(EmployeeEntity employeeEntity) {
        employeeRepository.save(employeeEntity);
        return "The given employee detail is successfully added to database";
    }

    @Override
    public EmployeeEntity getEmployeeById(int id) throws Exception {
        EmployeeEntity employee=employeeRepository.findById(id).orElse(null);
        if(employee!=null){
            return employee;
        }
        throw new Exception("The given id "+id+" is not there is the database");
    }

    @Override
    public String updateEmployee(EmployeeEntity employeeEntity, int id) {
        EmployeeEntity employee=employeeRepository.findById(id).orElse(null);
        if(employee!=null){
            employeeRepository.save(employeeEntity);
            return "Employee updated with new values";
        }
        return "The given id "+id+" is not present in the database";
    }

    @Override
    public String deleteEmployee(int id) {
        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
        if(employee!=null){
            employeeRepository.deleteById(id);
            return "Employee with id "+id+" deleted successfully";
        }
        return "The given id "+id+" is not found in the database";
    }

}
