package com.example.server1.mapper;

import com.example.server1.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    void save(@Param("student") Student student);
    Student findByEmail(@Param("email") String email);

    boolean updateById(Student student);
}
