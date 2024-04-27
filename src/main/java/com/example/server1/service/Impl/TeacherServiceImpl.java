package com.example.server1.service.Impl;

import com.example.server1.DTO.GradeDTO;
import com.example.server1.entity.Teacher;
import com.example.server1.mapper.TeacherMapper;
import com.example.server1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("teacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Teacher findByEmail(String email) {
        Teacher teacher;
        teacher = teacherMapper.findByEmail(email);
        return teacher;
    }

    @Override
    public void save(Teacher teacher) {
        System.out.println("teacher");
        teacherMapper.save(teacher);
    }

    @Override
    public boolean updateById(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    @Override
    public boolean inputGrade(GradeDTO gradeDTO) {
        return teacherMapper.inputGrade(gradeDTO);
    }
}
