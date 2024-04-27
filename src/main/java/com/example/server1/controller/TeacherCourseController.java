package com.example.server1.controller;

import com.example.server1.DTO.CourseDTO;
import com.example.server1.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherCourseController {
    private TeacherCourseService teacherCourseService;
    @Autowired
    public TeacherCourseController(TeacherCourseService teacherCourseService) {
        this.teacherCourseService = teacherCourseService;
    }
    @PostMapping("/courses")
    public Map<String, Object> getAllCourses(@RequestBody Map<String, Integer> paginationParams) {
        int offset = paginationParams.getOrDefault("offset", 0);
        int limit = paginationParams.getOrDefault("limit", 10); // 默认每页显示 10 条数据
        int teacherID = paginationParams.get("teacherID");
        List<CourseDTO> courses = teacherCourseService.getCourses(offset, limit, teacherID);
        int totalCount = teacherCourseService.getCount(teacherID); // 获取总记录数
        Map<String, Object> result = new HashMap<>();
        result.put("courses", courses);
        result.put("totalCount", totalCount);
        return result;
    }

    @PostMapping("/buildCourse")
    public boolean buildCourse(@RequestBody CourseDTO courseDTO) {
        System.out.println(courseDTO);
       return teacherCourseService.buildCourse(courseDTO);
//        return true;
    }
}
