package com.example.server1.controller;

import com.example.server1.DTO.EmailDTO;
import com.example.server1.Utils.Md5Util;
import com.example.server1.Utils.RandomUtil;
import com.example.server1.Utils.Result;
import com.example.server1.entity.Student;
import com.example.server1.service.EmailService;
import com.example.server1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.server1.Utils.UUIDUtils;

import java.io.File;
import java.io.IOException;
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
        System.out.println("验证码: " + code);
        String content = "验证码为 " + code + " ，五分钟有效，请妥善保管！";
        // 邮件内容
        emailDTO.setEmail(email);
        emailDTO.setTitle("学生信息管理系统——注册服务");
        emailDTO.setContent(content);

        // 查询用户
        Student student = studentService.findByEmail(email);
        if(student == null){
            //没被注册
            System.out.println("没被注册");
            System.out.println("准备向邮箱发送 " );
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

    @PostMapping("/register")
    public Result register(@RequestBody Student student) {
        System.out.println("正在验证学生注册：" + student);
        //还需要进行验证码判断
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        student.setPassword((Md5Util.getMD5String(student.getPassword())));
        studentService.save(student);
        operations.getOperations().delete(student.getEmail());
        return Result.success("注册成功");
    }


    @PostMapping("/login")
    public ResponseEntity<Student> login(@RequestBody Student student) {
        System.out.println("正在验证学生登录：" + student);
        Student s = studentService.findByEmail(student.getEmail());
        System.out.println("查询到的学生：" + s);
        if (s == null || !Md5Util.checkPassword(student.getPassword(), s.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else {
            System.out.println(s.getName() + "成功登录");
            return ResponseEntity.ok(s);
        }
    }

    @PostMapping("updateStudent")
    public boolean updateStudent(@RequestBody Student student) {

        System.out.println("更新 " + student);
        student.setPassword((Md5Util.getMD5String(student.getPassword())));
        System.out.println("更新加密 " + student);
        return studentService.updateById(student);
    }





}
