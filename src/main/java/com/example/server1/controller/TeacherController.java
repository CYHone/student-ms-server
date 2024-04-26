package com.example.server1.controller;

import com.example.server1.DTO.EmailDTO;
import com.example.server1.Utils.Md5Util;
import com.example.server1.Utils.RandomUtil;
import com.example.server1.Utils.Result;

import com.example.server1.entity.Student;
import com.example.server1.entity.Teacher;
import com.example.server1.service.EmailService;
import com.example.server1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/registerCode")
    public Result sendRegisterCode(@RequestBody String encodedEmail) {
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
        Teacher teacher = teacherService.findByEmail(email);
        System.out.println("teacher: " + teacher);
        if (teacher == null) {
            System.out.println("没被注册");
            System.out.println("准备向邮箱发送 ");
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

    @PostMapping("/register")
    public Result register(@RequestBody Teacher teacher) {
        System.out.println("正在验证学生注册：" + teacher);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        teacher.setPassword((Md5Util.getMD5String(teacher.getPassword())));
        teacherService.save(teacher);
        operations.getOperations().delete(teacher.getEmail());
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody Teacher teacher) {
        System.out.println("正在验证教师登录：" + teacher);
        Teacher t = teacherService.findByEmail(teacher.getEmail());
        System.out.println(" 查询到的老师 " + t);
        if (Md5Util.checkPassword(teacher.getPassword(), t.getPassword())){
            System.out.println("密码正确");

        }else {
            System.out.println("密码错误");
        }
        return ResponseEntity.ok(t);
    }

    @PostMapping("updateTeacher")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        System.out.println("正在更新教师信息：" + teacher);
        teacher.setPassword((Md5Util.getMD5String(teacher.getPassword())));
        System.out.println("更新加密后的教师信息：" + teacher);
        return teacherService.updateById(teacher);
    }

}
