package com.example.server1.service;

import com.example.server1.DTO.CourseDTO;
import com.example.server1.DTO.GradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherCourseService {
    List<CourseDTO> getCourses(int offset, int limit, int teacherID);


    int getCount(int teacherID);

    boolean buildCourse(CourseDTO courseDTO);

    List<GradeDTO> getGrade(int offset, int limit, String keyword, String type);

    List<GradeDTO> getCourseGrade(int courseID);
}
