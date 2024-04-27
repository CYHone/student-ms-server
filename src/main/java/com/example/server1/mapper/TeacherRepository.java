package com.example.server1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeacherRepository {
    @Update("UPDATE teacher set Avatar = #{url} WHERE TeacherID = #{teacherID}")
    void findByTeacherId(String teacherID, String url);
}
