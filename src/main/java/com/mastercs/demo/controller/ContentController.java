package com.mastercs.demo.controller;


import com.mastercs.demo.payload.Receive;
import com.mastercs.demo.bean.Search;
import com.mastercs.demo.repository.SearchDAO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentController {
    @Autowired
    SearchDAO2 searchDAO2;

    @Value("${file-path}")
    private String filePath;

    @PostMapping(value = "/content")   // 处理POST请求
    //@ResponseBody  // 使返回结果被解析为json数据

    public List<Search> findByTitle(@RequestBody Receive requestSearch) {

        String searchContent = requestSearch.getSearchContent();
//        System.out.println(searchContent);

        List<Search> tp = searchDAO2.findByTitle(searchContent);
//        System.out.println(tp);


//        返回搜索到的匹配的JSON数组
        return tp;
    }
}

