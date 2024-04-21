package com.example.server1.entity;

import lombok.Data;

@Data
public class SelectCourseRequest {
    private Integer courseID;
    private Integer studentID;
}
