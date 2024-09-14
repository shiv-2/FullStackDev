package com.gentech.student.controller;

import com.gentech.student.entity.Student;
import com.gentech.student.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class StudentController {
    private StudentService studService;
    public StudentController(StudentService studService){
        super();
        this.studService=studService;
    }
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studService.getStudents();
    }
}
