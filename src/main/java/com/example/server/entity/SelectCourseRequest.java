package com.example.server.entity;

import lombok.Data;

@Data
public class SelectCourseRequest {
    private Integer courseID;
    private Integer studentID;
}
