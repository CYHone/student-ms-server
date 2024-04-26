package com.example.server1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private int teacherID;
    private String teacherName;
    private String password;
    private String email;
    private String phoneNumber;
    private String avatar;



}




