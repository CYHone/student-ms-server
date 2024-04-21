package com.example.server1.service;

import com.example.server1.DTO.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDTO> getAllCourses();

    boolean selectCourse(Integer courseID, Integer studentID);


    List<CourseDTO> getAllMyCourses(Integer studentId);

    boolean deleteCourse(Integer deleteCourseId);
}
