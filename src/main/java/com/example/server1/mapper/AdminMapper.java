package com.example.server1.mapper;

import com.example.server1.DTO.AdminDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    @Select("SELECT * FROM school.admin WHERE Name = #{name}")
    AdminDTO login(@Param("name") String name);

    @Update("UPDATE school.teacher SET Password = #{password} WHERE TeacherID = #{teacherID}")
    boolean updateTeacherPassword(String password, int teacherID);

   @Update("UPDATE school.student SET Password = #{password} WHERE id = #{id}")
    boolean updateStudentPassword(String password, int id);
}
