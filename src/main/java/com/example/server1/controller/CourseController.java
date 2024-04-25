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

import java.util.List;
@RestController
@RequestMapping("/student")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
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
