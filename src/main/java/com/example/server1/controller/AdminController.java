package com.example.server1.controller;

import com.example.server1.DTO.AdminDTO;
import com.example.server1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/login")
    public int login(@RequestBody Map<String, Object> requestData) {
        int flag = 0;
        System.out.println(requestData);
        String name = (String) requestData.get("email");
        System.out.println("查询到"+name);
        AdminDTO adminDTO = adminService.login(name);
        System.out.println("查询到"+adminDTO);
        String password = (String) requestData.get("password");
        if(adminDTO.getPassword().equals(password)) {
            System.out.println("密码正确");
        }else {
            System.out.println("密码错误");
            flag = 1;

        }
     return flag;
    }

}
