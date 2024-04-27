package com.example.server1.service.Impl;

import com.example.server1.DTO.CourseDTO;
import com.example.server1.mapper.TeacherCourseMapper;
import com.example.server1.service.TeacherCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    private final TeacherCourseMapper teacherCourseMapper;

    public TeacherCourseServiceImpl(TeacherCourseMapper teacherCourseMapper) {
        this.teacherCourseMapper = teacherCourseMapper;
    }

    @Override
    public List<CourseDTO> getCourses(int offset, int limit, int teacherID) {
        return teacherCourseMapper.getCourses(offset, limit, teacherID);
    }

    @Override
    public int getCount(int teacherID) {
        return teacherCourseMapper.getCount(teacherID);
    }

    @Override
    public boolean buildCourse(CourseDTO courseDTO) {
        return teacherCourseMapper.buildCourse(courseDTO);
    }


}
