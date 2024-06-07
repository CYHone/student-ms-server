package com.example.server.service.Impl;

import com.example.server.entity.Student;
import com.example.server.mapper.StudentMapper;
import com.example.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("studentServiceImpl")
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


    public boolean updateById(Student student) {
            return studentMapper.updateById(student);
    }


}
