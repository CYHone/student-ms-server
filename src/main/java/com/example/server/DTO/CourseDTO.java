package com.example.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
