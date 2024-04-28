package com.example.server1.mapper;

import com.example.server1.DTO.AdminDTO;
import com.example.server1.DTO.GradeDTO;
import com.example.server1.DTO.SelectionCourseDTO;
import com.example.server1.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    @Select("SELECT * FROM school.admin WHERE Name = #{name}")
    AdminDTO login(@Param("name") String name);

    @Update("UPDATE school.teacher SET Password = #{password} WHERE TeacherID = #{teacherID}")
    boolean updateTeacherPassword(String password, int teacherID);

   @Update("UPDATE school.student SET Password = #{password} WHERE id = #{id}")
    boolean updateStudentPassword(String password, int id);

    @Select("SELECT * FROM  school.Grade WHERE id = #{keyword} ")
    List<GradeDTO> inquireStudentById(String keyword);

    @Select("SELECT g.* FROM school.Grade g INNER JOIN school.Student s ON g.id = s.id WHERE s.name = #{keyword}")
    List<GradeDTO> inquireStudentByName(String keyword);

    @Select("SELECT g.* FROM school.Grade g INNER JOIN school.Student s ON g.id = s.id WHERE s.email = #{keyword}")
    List<GradeDTO> inquireStudentByEmail(String keyword);

    @Select("SELECT * FROM courseselection where id = #{keyword}")
    List<SelectionCourseDTO> inquireStudentCourseById(String keyword);

    @Select("SELECT c.* FROM school.courseselection c INNER JOIN school.student s on c.id = s.id WHERE s.name = #{keyword}")
    List<SelectionCourseDTO> inquireStudentCourseByName(String keyword);

    @Select("SELECT c.* FROM school.courseselection c INNER JOIN school.student s on c.id = s.id WHERE s.email = #{keyword}")
    List<SelectionCourseDTO> inquireStudentCourseByEmail(String keyword);

    @Select("SELECT * FROM school.student WHERE id = #{keyword}")
    Student inquireStudentInfoById(String keyword);

    @Select("SELECT * FROM school.student WHERE name = #{keyword}")
    Student inquireStudentInfoByName(String keyword);

    @Select("SELECT * FROM school.student WHERE email = #{keyword}")
    Student inquireStudentInfoByEmail(String keyword);
}
