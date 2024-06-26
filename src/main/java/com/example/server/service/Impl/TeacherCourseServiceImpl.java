package com.example.server.service.Impl;

import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import com.example.server.mapper.TeacherCourseMapper;
import com.example.server.service.TeacherCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    private final TeacherCourseMapper teacherCourseMapper;

    public TeacherCourseServiceImpl(TeacherCourseMapper teacherCourseMapper) {
        this.teacherCourseMapper = teacherCourseMapper;
    }

    @Override
    public List<CourseDTO> getCourses(int offset, int limit, int teacherID) {
        return teacherCourseMapper.getCourses(offset, limit, teacherID);
    }

    @Override
    public int getCount(int teacherID) {
        return teacherCourseMapper.getCount(teacherID);
    }

    @Override
    public boolean buildCourse(CourseDTO courseDTO) {
        return teacherCourseMapper.buildCourse(courseDTO);
    }

    @Override
    public List<GradeDTO> getGrade(int offset, int limit, String keyword, String type) {
        if (type.equals("well")) {
            return teacherCourseMapper.getGradeByWell(offset, limit, keyword);
        } else if (type.equals("good")) {
            return teacherCourseMapper.getGradeByGood(offset, limit, keyword);
        } else if (type.equals("pass")) {
            return teacherCourseMapper.getGradeByPass(offset, limit, keyword);
        } else if (type.equals("noPass")) {
            return teacherCourseMapper.getGradeByNoPass(offset, limit, keyword);
        }
        return null;
    }

    @Override
    public List<GradeDTO> getCourseGrade(int courseID) {
        return teacherCourseMapper.getCourseGrade(courseID);
    }


}
