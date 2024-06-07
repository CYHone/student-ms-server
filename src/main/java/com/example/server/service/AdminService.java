package com.example.server.service;

import com.example.server.DTO.AdminDTO;
import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import com.example.server.DTO.SelectionCourseDTO;
import com.example.server.entity.Student;
import com.example.server.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    AdminDTO login(String name);

    boolean updateTeacherPassword(String password, int teacherID);

    boolean updateStudentPassword(String password, int id);

    List<GradeDTO> inquireStudent(String keyword, String type);

    List<SelectionCourseDTO> inquireStudentCourse(String keyword, String type);

   Student inquireStudentInfo(String keyword, String type);

    List<CourseDTO> inquireTeacher(String keyword, String type);

    Teacher inquireTeacherInfo(String keyword, String type);

    List<CourseDTO> getCourse(int courseID);

    boolean changeCourse(CourseDTO courseDTO);
}
