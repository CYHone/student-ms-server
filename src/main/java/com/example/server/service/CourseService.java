package com.example.server.service;

import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {


    boolean selectCourse(Integer courseID, Integer studentID);


    List<CourseDTO> getAllMyCourses(Integer studentId);

    boolean deleteCourse(Integer deleteCourseId);

    List<GradeDTO> getGrade(Integer studentId);

    int getTotalCount();
    List<CourseDTO> getAllCourses(int offset, int limit);


    List<CourseDTO> getCourses(int offset, int limit, String keyword, String type);

    int getCoursesCount(String keyword, String type);
}
