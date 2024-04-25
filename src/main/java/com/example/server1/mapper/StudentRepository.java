package com.example.server1.mapper;

import com.example.server1.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository  {

   @Update("UPDATE student SET  Avatar = #{filePath} WHERE id = #{studentId}")
   void findByStudentId(String studentId, String filePath);

   @Select("SELECT Avatar FROM student WHERE id = #{studentId}")
    String getAvatarStudentId(String studentId);
}
