package com.example.server1.DTO;

import lombok.Data;

@Data
public class CourseDTO {
    private Integer CourseID;
    private String CourseName;
    private  Integer TeacherID;
    private Integer Credit;
    private String Time;
    private String Classroom;
    private String TeacherName;
    private String Introduce;
    private Integer studentID;
}
