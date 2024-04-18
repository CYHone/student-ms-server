package com.example.server1.controller;

import com.example.server1.DTO.EmailDTO;
import com.example.server1.Utils.RandomUtil;
import com.example.server1.Utils.Result;
import com.example.server1.entity.Student;
import com.example.server1.service.EmailService;
import com.example.server1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
//@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("/registerCode")
    public Result sendRegisterCode(@RequestBody String encodedEmail){
        if (encodedEmail == null || encodedEmail.isEmpty()) {
            return Result.error("请输入邮箱！");
        }
        String email = URLDecoder.decode(encodedEmail, StandardCharsets.UTF_8);
        System.out.println("Decoded email: " + email);
        EmailDTO emailDTO = new EmailDTO();
        // 设置验证码
        String code = RandomUtil.getVerifyCode();
        String content = "验证码为 " + code + " ，五分钟有效，请妥善保管！";
        // 邮件内容
        emailDTO.setEmail(email);
        emailDTO.setTitle("学生信息管理系统——注册服务");
        emailDTO.setContent(content);

        // 查询用户
        Student student = studentService.findByEmail(email);
        if(student == null){
            //没被注册
            try {
                emailService.sendMsg(emailDTO);
                // 往Redis中存储一个键值对
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                operations.set(emailDTO.getEmail(), code, 300, TimeUnit.SECONDS);
                return Result.success();
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("邮件发送失败！");
            }
        }
        return Result.error("已被注册！");
    }



    @PostMapping("/addStudent")
    public boolean addStudent(@RequestBody Student student) {
        System.out.println("正在保存学生对象：" + student);
        return studentService.save(student);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Student student) {
        System.out.println("正在验证学生登录：" + student);
        Student s = studentService.findByEmail(student.getEmail());
        System.out.println("查询到的学生：" + s);
        if (s == null || !s.getPassword().equals(student.getPassword())) {
            return false;
        } else {
            return true;
        }
    }
}
