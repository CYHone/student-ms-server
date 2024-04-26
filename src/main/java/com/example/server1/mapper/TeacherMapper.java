package com.example.server1.mapper;

import com.example.server1.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMapper {
    void save(@Param("teacher") Teacher teacher);

    Teacher findByEmail(@Param("email") String email);

    boolean updateById(Teacher teacher);
}
