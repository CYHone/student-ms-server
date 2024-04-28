package com.example.server1.service.Impl;

import com.example.server1.DTO.AdminDTO;

import com.example.server1.DTO.GradeDTO;
import com.example.server1.DTO.SelectionCourseDTO;
import com.example.server1.entity.Student;
import com.example.server1.mapper.AdminMapper;
import com.example.server1.service.AdminService;
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
}
