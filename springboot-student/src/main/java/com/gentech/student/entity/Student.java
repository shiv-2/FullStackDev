package com.gentech.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stud_id")
    private Long id;
    @Column(name="student_name")
    private String studentName;
    @Column(name="course_name")
    private String courseName;
    @Column(name="city_name")
    private String cityName;

    public Student(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
