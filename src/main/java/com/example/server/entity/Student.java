package com.example.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Student")
public class Student {
    // 成员变量
    private Integer id;
    private String name;
    private String password;
    private String className;
    private String email;
    private String phone;
    private String code;
    private String Avatar;

}
