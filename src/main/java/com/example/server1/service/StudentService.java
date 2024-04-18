package com.example.server1.service;

import com.example.server1.entity.Student;

public interface StudentService {
    boolean save(Student student);

    Student findByEmail(String email);
}
