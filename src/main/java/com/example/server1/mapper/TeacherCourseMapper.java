package com.example.server1.mapper;

import com.example.server1.DTO.CourseDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface TeacherCourseMapper {
    // 分页查询得到本老师所有课程
    @Select("SELECT * FROM Course WHERE TeacherID = #{teacherID} LIMIT #{limit} OFFSET #{offset}")
    List<CourseDTO> getCourses(@Param("offset") int offset, @Param("limit") int limit, @Param("teacherID") int teacherID);

    // 分页查询得到本老师所有课程数量
    @Select("SELECT COUNT(*) FROM Course WHERE TeacherID = #{teacherID}")
    int getCount(int teacherID);

    boolean buildCourse(@Param("courseDTO") CourseDTO courseDTO);
}
