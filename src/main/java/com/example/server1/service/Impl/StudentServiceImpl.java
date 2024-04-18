package com.example.server1.service.Impl;

import com.example.server1.entity.Student;
import com.example.server1.mapper.StudentMapper;
import com.example.server1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentMapper studentMapper;


    @Override
    public void save(Student student) {
        studentMapper.save(student);
    }


    @Override
    public Student findByEmail(String email) {
        Student student;
        student = studentMapper.findByEmail(email);
        return student;
    }



}
