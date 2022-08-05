package com.mastercs.demo.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

@RestController
@CrossOrigin
public class FilesController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;
    @Value("${server.port}")
    private String serverPort;

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploads")
    public Object files(@RequestParam MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String uuid = UUID.randomUUID().toString();
        //获取后缀名
        String fix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = uuid + fix;
        String relativePath = fileUploadPath + fileName;
        int len = 0;
        //设置缓冲区
        byte[] bytes = new byte[1024];
        File folder = new File(fileUploadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(relativePath);
        while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
        }
        fileOutputStream.close();
        inputStream.close();
        HashMap<String, String> map = new HashMap<>();
        map.put("absolutePath", "http://" + serverIp + ":" + serverPort + "/view?relativePath=" + fileName);

        map.put("relativePath", relativePath);
        return map;

    }

    /**
     * 文件展示
     * @param relativePath
     * @param response
     * @throws IOException
     */
    @GetMapping("/view")
    public void view(@RequestParam String relativePath, HttpServletResponse response) throws IOException {
        String[] split = relativePath.split("/");
        String fileUUID = split[split.length - 1];
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + relativePath);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }


}