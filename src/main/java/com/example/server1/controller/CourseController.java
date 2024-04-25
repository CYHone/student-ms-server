package com.example.server1.controller;

import com.example.server1.DTO.CourseDTO;
import com.example.server1.DTO.GradeDTO;
import com.example.server1.entity.SelectCourseRequest;
import com.example.server1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public Map<String, Object> getAllCourses(@RequestBody Map<String, Integer> paginationParams) {
        int offset = paginationParams.getOrDefault("offset", 0);
        int limit = paginationParams.getOrDefault("limit", 10); // 默认每页显示 10 条数据
        List<CourseDTO> courses = courseService.getAllCourses(offset, limit);
        int totalCount = courseService.getTotalCount(); // 获取总记录数
        Map<String, Object> result = new HashMap<>();
        result.put("courses", courses);
        result.put("totalCount", totalCount);
        return result;
    }

    @PostMapping("/searchCourse")
    public Map<String, Object> getCourses(@RequestBody Map<String, Object> requestData) {
        int offset = (int) requestData.getOrDefault("offset", 0);
        int limit = (int) requestData.getOrDefault("limit", 10);
        String keyword = (String) requestData.getOrDefault("keyword", "");
        String type = (String) requestData.getOrDefault("type", "courseName");

        List<CourseDTO> courses = courseService.getCourses(offset, limit, keyword, type);
        int totalCount = courseService.getCoursesCount(keyword, type);

        Map<String, Object> result = new HashMap<>();
        result.put("courses", courses);
        result.put("totalCount", totalCount);
        return result;
    }

    @PostMapping("/selectCourse")
    public boolean selectCourse(@RequestBody SelectCourseRequest request) {
        Integer courseID = request.getCourseID();
        System.out.println("课程号"+courseID);
        Integer studentID = request.getStudentID();
        System.out.println("学号"+studentID);
        return courseService.selectCourse(courseID, studentID);
    }

    @PostMapping("/myCourses")
    public List<CourseDTO> getAllMyCourses(@RequestBody Integer studentId) {
        System.out.println("学号" + studentId);
        return courseService.getAllMyCourses(studentId);
    }
    @PostMapping("/deleteCourses")
    public boolean deleteCourse(@RequestBody Integer  deleteCourseId ){
        System.out.println("删除课程ID" + deleteCourseId);
        return courseService.deleteCourse(deleteCourseId);
    }
    @PostMapping("/grade")
    public List<GradeDTO> getGrade(@RequestBody Integer studentId){
        System.out.println("学号" + studentId);
        return courseService.getGrade(studentId);
    }

}
