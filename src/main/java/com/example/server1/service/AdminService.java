package com.example.server1.service;

import com.example.server1.DTO.AdminDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    AdminDTO login(String name);

    boolean updateTeacherPassword(String password, int teacherID);

    boolean updateStudentPassword(String password, int id);
}
