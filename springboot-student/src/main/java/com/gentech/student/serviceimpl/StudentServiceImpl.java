package com.gentech.student.serviceimpl;

import com.gentech.student.entity.Student;
import com.gentech.student.repository.StudentRepository;
import com.gentech.student.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepo;
    public StudentServiceImpl(StudentRepository studentRepo){
        super();
        this.studentRepo=studentRepo;
    }
    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
}
