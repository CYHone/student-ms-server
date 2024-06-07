package com.example.server.controller;

import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import com.example.server.service.TeacherCourseService;
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
    }
    @PostMapping("searchGrade")
    public Map<String, Object> getGrade(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData);
        int offset = (int) requestData.getOrDefault("offset", 0);
        int limit = (int) requestData.getOrDefault("limit", 5);
        String keyword = (String) requestData.getOrDefault("keyword", "");
        String type = (String) requestData.getOrDefault("type", "");
        List<GradeDTO> grades = teacherCourseService.getGrade(offset, limit, keyword, type);
//        int totalCount = teacherCourseService.getGradeCount(keyword, type);
//
        Map<String, Object> result = new HashMap<>();
       result.put("grades", grades);
//        result.put("totalCount", totalCount);

       return result;
    }

//    @PostMapping("/grade")
//    public List<GradeDTO> getGrade(@RequestBody Integer studentId){
//        System.out.println("学号" + studentId);
//        return courseService.getGrade(studentId);
//    }
    @PostMapping("/searchCourse")
    public List<GradeDTO> getCourses(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData);
        int CourseID = (int) requestData.getOrDefault("CourseID", 0);
        return teacherCourseService.getCourseGrade(CourseID);
    }

}
