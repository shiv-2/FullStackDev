package com.gentech.employee.controller;

import com.gentech.employee.entity.EmployeeEntity;
import com.gentech.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //http://localhost:8082/v1/api/getEmployee
    @GetMapping("/getEmployee")
    public List<EmployeeEntity> getAllEmployeeDetails(){
        return employeeService.getAllEmployee();
    }
    //http://localhost:8082/v1/api/addEmployee
    @PostMapping("/addEmployee")
    public String addEmployeeDetails(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.addEmployee(employeeEntity);
    }
    //http://localhost:8082/v1/api/getEmployeeById/{id}
    @GetMapping("/getEmployeeById/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable int id) throws Exception {
        return employeeService.getEmployeeById(id);
    }
    //http://localhost:8082/v1/api/updateEmployee/{id}
    @PutMapping("/updateEmployee/{id}")
    public String updateCustomerById(@PathVariable int id,@RequestBody EmployeeEntity employeeEntity){
        return employeeService.updateEmployee(employeeEntity,id);
    }
    //http://localhost:8082/v1/api/deleteEmployee/{id}
    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployeeById(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
