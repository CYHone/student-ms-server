package com.example.server1.service.Impl;


import com.example.server1.DTO.CourseDTO;
import com.example.server1.mapper.CourseMapper;
import com.example.server1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService {

    private final CourseMapper courseMapper;
    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }
    @Override
    public List<CourseDTO> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    @Override
    public boolean selectCourse(Integer courseID, Integer studentID) {
        return courseMapper.selectCourse(courseID,studentID);
    }

    @Override
    public List<CourseDTO> getAllMyCourses(Integer studentId) {
        return courseMapper.getAllMyCourses(studentId);
    }

    @Override
    public boolean deleteCourse(Integer deleteCourseId) {
        return courseMapper.deleteCourse(deleteCourseId);
    }


}
