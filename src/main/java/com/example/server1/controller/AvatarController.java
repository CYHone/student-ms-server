package com.example.server1.controller;

import com.example.server1.Utils.AliOSSUtils;
import com.example.server1.mapper.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

public class AvatarController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/uploadAvatar")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile image,@RequestParam("studentId") String studentId) {
        try {
            String url = aliOSSUtils.upload(image);
            System.out.println("图片的地址"+url);
            studentRepository.findByStudentId(studentId,url);
            System.out.println("返回前端"+url);
            System.out.println("存于数据库"+url);
            //avatarService.storeAvatar(image,studentId);
            return ResponseEntity.ok(url);
        } catch (IllegalArgumentException e) {
            // 参数错误，比如上传的文件为空
            return ResponseEntity.badRequest().body("上传的文件为空或不存在");
        } catch (IOException e) {
            // 文件上传失败
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }


}
