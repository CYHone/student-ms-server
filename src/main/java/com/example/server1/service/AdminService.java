package com.example.server1.service;

import com.example.server1.DTO.AdminDTO;
import com.example.server1.DTO.GradeDTO;
import com.example.server1.DTO.SelectionCourseDTO;
import com.example.server1.entity.Student;
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
}
