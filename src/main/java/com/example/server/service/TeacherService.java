package com.example.server.service;

import com.example.server.DTO.GradeDTO;
import com.example.server.entity.Teacher;

public interface TeacherService {
    Teacher findByEmail(String email) ;

    void save(Teacher teacher) ;

    boolean updateById(Teacher teacher);

    boolean inputGrade(GradeDTO gradeDTO);
}
