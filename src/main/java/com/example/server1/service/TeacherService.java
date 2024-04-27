package com.example.server1.service;

import com.example.server1.DTO.GradeDTO;
import com.example.server1.entity.Teacher;

public interface TeacherService {
    Teacher findByEmail(String email) ;

    void save(Teacher teacher) ;

    boolean updateById(Teacher teacher);

    boolean inputGrade(GradeDTO gradeDTO);
}
