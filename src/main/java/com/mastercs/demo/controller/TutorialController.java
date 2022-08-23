package com.mastercs.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mastercs.demo.bean.Tutorial;
import com.mastercs.demo.bean.Result;
import com.mastercs.demo.payload.PageInfo;
import com.mastercs.demo.payload.TutorialDTO;
import com.mastercs.demo.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @Value("${file-path}")
    private String filePath;

    @GetMapping("/list")
    public Result<?> list() {
        return Result.success(tutorialService.list());
    }

    @PostMapping("/page")
    public Result<?> findPage(@RequestBody PageInfo query) {

        QueryWrapper<Tutorial> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<Tutorial> page = tutorialService.page(new Page<>(query.getPageNum(), query.getPageSize()), queryWrapper);

        Page<TutorialDTO> result = new Page<>();
        List<TutorialDTO> list = new ArrayList<>();
        BeanUtil.copyProperties(page, result);
        page.getRecords().forEach(u -> {
            TutorialDTO tutorialDTO = new TutorialDTO();
            BeanUtil.copyProperties(u, tutorialDTO);
            if (tutorialDTO.getVideo() != null) {
                tutorialDTO.setAbsoluteVideo(filePath + u.getVideo());
            }
            list.add(tutorialDTO);
        });
        result.setRecords(list);
        return Result.success(result);
    }



    @GetMapping("/queryById")
    public Result<?> queryById(@RequestParam("id") Integer id) {
        Tutorial byId = tutorialService.getById(id);
        return Result.success(byId);
    }

}
