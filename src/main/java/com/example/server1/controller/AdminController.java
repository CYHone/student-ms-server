package com.example.server1.controller;

import com.example.server1.DTO.AdminDTO;
import com.example.server1.DTO.GradeDTO;
import com.example.server1.DTO.SelectionCourseDTO;
import com.example.server1.Utils.Md5Util;
import com.example.server1.entity.Student;
import com.example.server1.entity.Teacher;
import com.example.server1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/login")
    public int login(@RequestBody Map<String, Object> requestData) {
        int flag = 0;
        System.out.println(requestData);
        String name = (String) requestData.get("email");
        System.out.println("查询到"+name);
        AdminDTO adminDTO = adminService.login(name);
        System.out.println("查询到"+adminDTO);
        String password = (String) requestData.get("password");
        if(adminDTO.getPassword().equals(password)) {
            System.out.println("密码正确");
        }else {
            System.out.println("密码错误");
            flag = 1;

        }
     return flag;
    }

    @PostMapping("/updateTeacher")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        System.out.println("正在更新教师信息：" + teacher);
        teacher.setPassword((Md5Util.getMD5String(teacher.getPassword())));
        System.out.println("更新加密后的教师信息：" + teacher);
        String password = teacher.getPassword();
        System.out.println("更新加密后的密码信息：" + password);
        int teacherID = teacher.getTeacherID();
        return adminService.updateTeacherPassword(password, teacherID);
    }
    @PostMapping("/updateStudent")
    public boolean updateStudent(@RequestBody Student student) {
        System.out.println("正在更新学生信息：" + student);
        student.setPassword((Md5Util.getMD5String(student.getPassword())));
        System.out.println("更新加密后的学生信息：" + student);
        String password = student.getPassword();
        System.out.println("更新加密后的密码信息：" + password);
        int id= student.getId();
        return adminService.updateStudentPassword(password, id);
    }
    @PostMapping("/inquireStudent")
    public Map<String, Object> inquireStudent(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData);
        String keyword = (String) requestData.getOrDefault("keyword", "");
        String type = (String) requestData.getOrDefault("type", "");
        if (type.equals("id")) {
            // 强转int
            keyword = String.valueOf(Integer.parseInt(keyword));
        }
        System.out.println("keyword "+keyword);
        System.out.println("type "+type);
        List<GradeDTO> grades = adminService.inquireStudent(keyword, type);
        List<SelectionCourseDTO> courses = adminService.inquireStudentCourse(keyword, type);
        Student student = adminService.inquireStudentInfo(keyword, type);
        Map<String, Object> result = new HashMap<>();
        result.put("grades", grades);
        result.put("courses", courses);
        result.put("student", student);
        return result;
    }
    @PostMapping("/inquireTeacher")
    public Map<String, Object> inquireTeacher(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData);
        String keyword = (String) requestData.getOrDefault("keyword", "");
        String type = (String) requestData.getOrDefault("type", "");

        return null;
    }
}
