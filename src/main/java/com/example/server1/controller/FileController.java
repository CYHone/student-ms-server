package com.example.server1.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileController {

    // 将该文件夹的路径赋值给 fileUploadPath
    @Value("${files.upload.path}")
    private String fileUploadPath;

    // 处理文件上传请求
    @PostMapping("/upload/img")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); // 获取文件原始名
        String type = FileUtil.extName(originalFilename); // 获取文件后缀
        long size = file.getSize(); // 获取文件大小

        // 先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        System.out.println(uploadParentFile);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        System.out.println(uploadFile);
        // 文件路径与下载接口路径一样
        String url = "http://localhost:8080/file/" + fileUUID;
        // 把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);
        System.out.println(url);
        System.out.println("上传完成");
        // 存入数据库（根据项目需求）
        // Data_Resource data_resource = new Data_Resource(); // 数据库实体类
        // data_resource.setR_name(originalFilename);
        // datasMapper.Insert_res(data_resource);
        return url;
    }

    /**
     * 通过流的形式下载
     * @param fileUUID
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Context-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        // 读取字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
