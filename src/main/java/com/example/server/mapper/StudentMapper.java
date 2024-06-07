package com.example.server.mapper;

import com.example.server.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    //传来参数
    void save(@Param("student") Student student);
    Student findByEmail(@Param("email") String email);

    boolean updateById(Student student);
}
