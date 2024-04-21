package com.example.server1.mapper;

import com.example.server1.DTO.CourseDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from Course")
    List<CourseDTO>getAllCourses();

    boolean selectCourse(Integer courseID, Integer studentID);

    @Select("SELECT c.* FROM Course c JOIN CourseSelection cs ON c.CourseID = cs.CourseID WHERE cs.id = #{studentId}")
    List<CourseDTO> getAllMyCourses(Integer studentId);

    @Delete("delete from CourseSelection where CourseID = #{deleteCourseId} ")
    boolean deleteCourse(Integer deleteCourseId);
}
