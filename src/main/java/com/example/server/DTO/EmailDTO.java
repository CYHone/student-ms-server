package com.example.server.DTO;

import lombok.Data;

@Data
public class EmailDTO {
    // 收件邮箱
    private String email;
    // 标题
    private String title;
    // 内容
    private String content;
}
