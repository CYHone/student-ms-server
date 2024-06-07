package com.example.server.service.Impl;


import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import com.example.server.mapper.CourseMapper;
import com.example.server.mapper.GradeMapper;
import com.example.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService {

    private final CourseMapper courseMapper;
    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

   private GradeMapper gradeMapper;
    @Autowired
    public void setGradeMapper(GradeMapper gradeMapper) {
        this.gradeMapper = gradeMapper;
    }


    @Override
    public boolean selectCourse(Integer courseID, Integer studentID) {
        return courseMapper.selectCourse(courseID,studentID);
    }

    @Override
    public List<CourseDTO> getAllMyCourses(Integer studentId) {
        return courseMapper.getAllMyCourses(studentId);
    }

    @Override
    public boolean deleteCourse(Integer deleteCourseId) {
        return courseMapper.deleteCourse(deleteCourseId);
    }

    @Override
    public List<GradeDTO> getGrade(Integer studentId) {
        return gradeMapper.getGrade(studentId);
    }

    @Override
    public int getTotalCount() {
        return courseMapper.getTotalCount();
    }
    @Override
    public List<CourseDTO> getAllCourses(int offset, int limit) {
        return courseMapper.getAllCourses(offset, limit);
    }

    @Override
    public List<CourseDTO> getCourses(int offset, int limit, String keyword, String type) {
        switch (type) {
            case "courseID":
                return courseMapper.getCoursesByCourseID(offset, limit, keyword);
            case "courseName":
                return courseMapper.getCoursesByCourseName(offset, limit, keyword);
            case "teacherID":
                return courseMapper.getCoursesByTeacherID(offset, limit, keyword);
            case "teacherName":
                return courseMapper.getCoursesByTeacherName(offset, limit, keyword);
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public int getCoursesCount(String keyword, String type) {
        switch (type) {
            case "courseID":
                return courseMapper.getCoursesCountByCourseID(keyword);
            case "courseName":
                return courseMapper.getCoursesCountByCourseName(keyword);
            case "teacherID":
                return courseMapper.getCoursesCountByTeacherID(keyword);
            case "teacherName":
                return courseMapper.getCoursesCountByTeacherName(keyword);
            default:
                return 0;
        }
    }


}
