package com.mastercs.demo.controller;

import cn.hutool.core.io.FileUtil;
import com.mastercs.demo.bean.Result;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
@CrossOrigin
public class FilesController {

    @Value("${files.upload.path}")
    private String fileUploadPath;
    @Value("${file-path}")
    private String filePath;
    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploads")
    public Result files(@RequestParam MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String uuid = UUID.randomUUID().toString();
        //获取后缀名
        String fix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = uuid + fix;

        Date date = new Date();
        String folderPath = new SimpleDateFormat("yyyy/MM/dd/").format(date);

        String absolutePath = fileUploadPath +folderPath+ fileName;
        String serverPath = filePath +folderPath+ fileName;
        String relativePath = folderPath+ fileName;

        int len = 0;
        //设置缓冲区
        byte[] bytes = new byte[1024];
        File folder = new File(fileUploadPath +folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
        while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
        }
        fileOutputStream.close();
        inputStream.close();
        HashMap<String, String> map = new HashMap<>();
        map.put("absolutePath", serverPath);
        map.put("relativePath", relativePath);
        return Result.success(map);

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
