package com.example.server1.mapper;

import com.example.server1.DTO.GradeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GradeMapper {
    @Select("SELECT * FROM grade WHERE id = #{studentId}")
    List<GradeDTO> getGrade(Integer studentId);
}
