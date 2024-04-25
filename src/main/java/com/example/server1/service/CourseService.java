package com.example.server1.service;

import com.example.server1.DTO.CourseDTO;
import com.example.server1.DTO.GradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDTO> getAllCourses();

    boolean selectCourse(Integer courseID, Integer studentID);


    List<CourseDTO> getAllMyCourses(Integer studentId);

    boolean deleteCourse(Integer deleteCourseId);

    List<GradeDTO> getGrade(Integer studentId);
}
