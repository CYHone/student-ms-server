package com.example.server1.controller;

import com.example.server1.entity.Student;
import com.example.server1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public boolean addStudent(@RequestBody Student student) {
        System.out.println("正在保存学生对象：" + student);
        return studentService.save(student);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Student student) {
        System.out.println("正在验证学生登录：" + student);
        Student s = studentService.findByEmail(student.getEmail());
        System.out.println("查询到的学生：" + s);
        if (s == null || !s.getPassword().equals(student.getPassword())) {
            return false;
        } else {
            return true;
        }
    }
}
