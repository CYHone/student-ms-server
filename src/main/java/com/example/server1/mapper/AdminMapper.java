package com.example.server1.mapper;

import com.example.server1.DTO.AdminDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    @Select("SELECT * FROM school.admin WHERE Name = #{name}")
    AdminDTO login(@Param("name") String name);
}
