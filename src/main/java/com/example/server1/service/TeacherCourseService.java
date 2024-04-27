package com.example.server1.service;

import com.example.server1.DTO.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherCourseService {
    List<CourseDTO> getCourses(int offset, int limit, int teacherID);


    int getCount(int teacherID);

    boolean buildCourse(CourseDTO courseDTO);
}
