package com.example.server1.mapper;

import com.example.server1.DTO.GradeDTO;
import com.example.server1.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMapper {
    void save(@Param("teacher") Teacher teacher);

    Teacher findByEmail(@Param("email") String email);

    boolean updateById(Teacher teacher);

    // 加入@Param会有错误
    @Insert("INSERT INTO Grade(CourseID, id, Score) VALUES (#{courseID}, #{id}, #{score})")
    boolean inputGrade( GradeDTO gradeDTO);
}
