package com.example.server1.mapper;

import com.example.server1.DTO.CourseDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {


    boolean selectCourse(Integer courseID, Integer studentID);

    @Select("SELECT c.* FROM Course c JOIN CourseSelection cs ON c.CourseID = cs.CourseID WHERE cs.id = #{studentId}")
    List<CourseDTO> getAllMyCourses(Integer studentId);

    @Delete("delete from CourseSelection where CourseID = #{deleteCourseId} ")
    boolean deleteCourse(Integer deleteCourseId);

    // 分页查询得到所有课程
    @Select("SELECT * FROM Course LIMIT #{limit} OFFSET #{offset}")
    List<CourseDTO> getAllCourses(@Param("offset") int offset, @Param("limit") int limit);
    // 分页查询得到所有课程数量
    @Select("SELECT COUNT(*) FROM Course")
    int getTotalCount();

    @Select("SELECT * FROM Course WHERE CourseName LIKE CONCAT('%', #{keyword}, '%') LIMIT #{limit} OFFSET #{offset}")
    List<CourseDTO> getCoursesByCourseName(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);

    @Select("SELECT * FROM Course WHERE CourseID LIKE CONCAT('%', #{keyword}, '%') LIMIT #{limit} OFFSET #{offset}")
    List<CourseDTO> getCoursesByCourseID(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);

    @Select("SELECT * FROM Course WHERE TeacherID LIKE CONCAT('%', #{keyword}, '%') LIMIT #{limit} OFFSET #{offset}")
    List<CourseDTO> getCoursesByTeacherID(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);

    @Select("SELECT * FROM Course WHERE TeacherName LIKE CONCAT('%', #{keyword}, '%') LIMIT #{limit} OFFSET #{offset}")
    List<CourseDTO> getCoursesByTeacherName(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);

    @Select("SELECT COUNT(*) FROM Course WHERE CourseID LIKE CONCAT('%', #{keyword}, '%')")
    int getCoursesCountByCourseID(String keyword);

    @Select("SELECT COUNT(*) FROM Course WHERE CourseName LIKE CONCAT('%', #{keyword}, '%')")
    int getCoursesCountByCourseName(String keyword);

    @Select("SELECT COUNT(*) FROM Course WHERE TeacherID LIKE CONCAT('%', #{keyword}, '%')")
    int getCoursesCountByTeacherID(String keyword);

    @Select("SELECT COUNT(*) FROM Course WHERE TeacherName LIKE CONCAT('%', #{keyword}, '%')")
    int getCoursesCountByTeacherName(String keyword);
}
