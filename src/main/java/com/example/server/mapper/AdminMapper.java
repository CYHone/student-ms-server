package com.example.server.mapper;

import com.example.server.DTO.AdminDTO;
import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.GradeDTO;
import com.example.server.DTO.SelectionCourseDTO;
import com.example.server.entity.Student;
import com.example.server.entity.Teacher;
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

    @Select("SELECT * FROM school.course WHERE TeacherID = #{keyword}")
    List<CourseDTO> inquireTeacherById(String keyword);

    @Select("SELECT * FROM school.course WHERE TeacherName = #{keyword}")
    List<CourseDTO> inquireTeacherByName(String keyword);

    @Select("SELECT c.* FROM school.course c INNER JOIN school.teacher t on c.TeacherID = t.TeacherID WHERE t.Email = #{keyword}")
    List<CourseDTO> inquireTeacherByEmail(String keyword);

    @Select("SELECT * FROM school.teacher WHERE TeacherID = #{keyword}")
    Teacher inquireTeacherInfoById(String keyword);

    @Select("SELECT * FROM school.teacher WHERE TeacherName = #{keyword}")
    Teacher inquireTeacherInfoByName(String keyword);

    @Select("SELECT * FROM school.teacher WHERE Email = #{keyword}")
    Teacher inquireTeacherInfoByEmail(String keyword);

    @Select("SELECT * FROM school.course WHERE CourseID = #{keyword}")
    List<CourseDTO> getCourse(int courseID);
@Update("UPDATE school.course SET CourseName = #{CourseName}, TeacherID = #{TeacherID}, Credit = #{Credit}, Time = #{Time}, Classroom = #{Classroom}, TeacherName = #{TeacherName}, Introduce = #{Introduce} WHERE CourseID = #{CourseID}")
 boolean changeCourse(CourseDTO courseDTO);
}
