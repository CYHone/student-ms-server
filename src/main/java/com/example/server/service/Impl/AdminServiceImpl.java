package com.example.server.service.Impl;

import com.example.server.DTO.AdminDTO;

import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import com.example.server.DTO.SelectionCourseDTO;
import com.example.server.entity.Student;
import com.example.server.entity.Teacher;
import com.example.server.mapper.AdminMapper;
import com.example.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminDTO login(String name) {
        return adminMapper.login(name);
    }

    @Override
    public boolean updateTeacherPassword(String password, int teacherID) {
        return adminMapper.updateTeacherPassword(password, teacherID);
    }

    @Override
    public boolean updateStudentPassword(String password, int id) {
        return adminMapper.updateStudentPassword(password, id);
    }

    @Override
    public List<GradeDTO> inquireStudent(String keyword, String type) {
        if (type.equals("id")) {
            return adminMapper.inquireStudentById(keyword);
        } else if (type.equals("name")) {
            return adminMapper.inquireStudentByName(keyword);
        }else if(type.equals("email")){
            return adminMapper.inquireStudentByEmail(keyword);
        }
        return null;
    }

    @Override
    public List<SelectionCourseDTO> inquireStudentCourse(String keyword, String type) {
        if (type.equals("id")) {
            return adminMapper.inquireStudentCourseById(keyword);
        } else if (type.equals("name")) {
            return adminMapper.inquireStudentCourseByName(keyword);
        } else if (type.equals("email")) {
            return adminMapper.inquireStudentCourseByEmail(keyword);
        }
        return null;
    }

    @Override
    public Student inquireStudentInfo(String keyword, String type) {
        if (type.equals("id")) {
            return adminMapper.inquireStudentInfoById(keyword);
        } else if (type.equals("name")) {
            return adminMapper.inquireStudentInfoByName(keyword);
        } else if (type.equals("email")) {
            return adminMapper.inquireStudentInfoByEmail(keyword);
        }
        return null;
    }

    @Override
    public List<CourseDTO> inquireTeacher(String keyword, String type) {
        if (type.equals("teacherID")){
            return adminMapper.inquireTeacherById(keyword);
        }
        if (type.equals("teacherName")){
            return adminMapper.inquireTeacherByName(keyword);
        }
        if (type.equals("email")){
            return adminMapper.inquireTeacherByEmail(keyword);
        }
        return null;
    }

    @Override
    public Teacher inquireTeacherInfo(String keyword, String type) {
        if (type.equals("teacherID")){
            return adminMapper.inquireTeacherInfoById(keyword);
        }
        if (type.equals("teacherName")){
            return adminMapper.inquireTeacherInfoByName(keyword);
        }
        if (type.equals("email")){
            return adminMapper.inquireTeacherInfoByEmail(keyword);
        }
        return null;
    }

    @Override
    public List<CourseDTO> getCourse(int courseID) {
        return adminMapper.getCourse(courseID);
    }

    @Override
    public boolean changeCourse(CourseDTO courseDTO) {
        return adminMapper.changeCourse(courseDTO);
    }
}
