package com.example.server.service;

import com.example.server.entity.Student;

public interface StudentService {
    void save(Student student);

    Student findByEmail(String email);

    boolean updateById(Student student);
}
