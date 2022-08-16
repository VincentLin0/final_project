package com.mastercs.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mastercs.demo.bean.entity.FavorRecords;
import com.mastercs.demo.bean.entity.Knowledge;
import com.mastercs.demo.config.Result;
import com.mastercs.demo.dto.KnowledgeDTO;
import com.mastercs.demo.mapper.FavorRecordsMapper;
import com.mastercs.demo.payload.Receive;
import com.mastercs.demo.repository.SearchDAO;
import com.mastercs.demo.services.KnowledgeService;
import com.mastercs.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    SearchDAO searchDAO;

    @Autowired
    KnowledgeService knowledgeService;

    @Autowired
    FavorRecordsMapper favorRecordsMapper;

    @Value("${file-path}")
    private String filePath;

    @PostMapping(value = "/result")   // 处理POST请求
    public Result<?> findByTitle(@RequestBody Receive requestSearch) {
        //分页查询
        LambdaQueryWrapper<Knowledge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Knowledge::getTitle, requestSearch.getSearchContent());
        queryWrapper.or().like(Knowledge::getDescription, requestSearch.getSearchContent());
        queryWrapper.orderByDesc(Knowledge::getId);
        Page<Knowledge> page = knowledgeService.page(new Page<>(requestSearch.getPageNum(), requestSearch.getPageSize()), queryWrapper);

        Page<KnowledgeDTO> returnPage = new Page<>();
        BeanUtil.copyProperties(page, returnPage);
        List<Knowledge> records = page.getRecords();
        List<KnowledgeDTO> list = new ArrayList<>();
        records.forEach(u -> {
            KnowledgeDTO knowledgeDTO = new KnowledgeDTO();
            Knowledge knowledge = setCoverUrl(u);
            BeanUtil.copyProperties(knowledge, knowledgeDTO);
            list.add(knowledgeDTO);
        });
        returnPage.setRecords(list);
        return Result.success(returnPage);
    }

    //collect or cancel collect
    @PostMapping("/collect")
    public Result<?> collect(@RequestBody FavorRecords favorRecords, HttpServletRequest request) {
        //select favor id and user id
        Integer userId = JwtUtils.getUserId(request);
        LambdaQueryWrapper<FavorRecords> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FavorRecords::getKnowledgeId, favorRecords.getKnowledgeId())
                .eq(FavorRecords::getUserId, userId)
                .last("limit 1");
        FavorRecords info = favorRecordsMapper.selectOne(wrapper);
        if (info != null) {
            favorRecordsMapper.deleteById(info.getId());
        } else {
            favorRecords.setUserId(userId);
            favorRecordsMapper.insert(favorRecords);
        }

        return Result.success();
    }

    @PostMapping(value = "/page")   // 处理POST请求
    public Result<?> page(@RequestBody Receive requestSearch) {
        //分页查询
        LambdaQueryWrapper<Knowledge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Knowledge::getId);
        Page<Knowledge> page = knowledgeService.page(new Page<>(requestSearch.getPageNum(), requestSearch.getPageSize()), queryWrapper);
        Page<KnowledgeDTO> returnPage = new Page<>();
        BeanUtil.copyProperties(page, returnPage);
        List<Knowledge> records = page.getRecords();
        List<KnowledgeDTO> list = new ArrayList<>();
        records.forEach(u -> {
            KnowledgeDTO knowledgeDTO = new KnowledgeDTO();
            Knowledge knowledge = setCoverUrl(u);
            BeanUtil.copyProperties(knowledge, knowledgeDTO);
            list.add(knowledgeDTO);
        });
        returnPage.setRecords(list);
        return Result.success(returnPage);
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Knowledge knowledge) {
        knowledgeService.saveOrUpdate(knowledge);
        return Result.success();
    }

    @PostMapping("/delBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids, HttpServletRequest request) {
        Integer userId = JwtUtils.getUserId(request);
        knowledgeService.removeByIds(ids);
        for (Integer id : ids) {
            LambdaQueryWrapper<FavorRecords> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(FavorRecords::getKnowledgeId, id)
                    .eq(FavorRecords::getUserId, userId)
                    .last("limit 1");
            FavorRecords favorRecords = favorRecordsMapper.selectOne(wrapper);
            if (favorRecords != null) {
                favorRecordsMapper.deleteById(favorRecords.getId());
            }
        }
        return Result.success();
    }

    @GetMapping("/queryById")
    public Result<?> queryById(@RequestParam("id") Integer id, HttpServletRequest request) {
        Knowledge result = knowledgeService.getById(id);
        KnowledgeDTO knowledgeDTO = new KnowledgeDTO();
        String token = request.getHeader("token");
        if ("null".equals(token)) {
            BeanUtil.copyProperties(setCoverUrl(result), knowledgeDTO);
            return Result.success(knowledgeDTO);
        }else{
            Integer userId = JwtUtils.getUserId(request);
            LambdaQueryWrapper<FavorRecords> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(FavorRecords::getKnowledgeId, id)
                    .eq(FavorRecords::getUserId, userId)
                    .last("limit 1");
            FavorRecords favorRecords = favorRecordsMapper.selectOne(wrapper);
            Knowledge knowledge = setCoverUrl(result);
            BeanUtil.copyProperties(knowledge, knowledgeDTO);
            if (favorRecords != null) {
                knowledgeDTO.setIsCollect(true);
            } else {
                knowledgeDTO.setIsCollect(false);
            }
            return Result.success(knowledgeDTO);
        }

    }

    private Knowledge setCoverUrl(Knowledge u) {
        if (u.getPic1() != null) {
            u.setPic1(filePath + u.getPic1());
        }
        if (u.getPic2() != null) {
            u.setPic2(filePath + u.getPic2());
        }
        if (u.getPic3() != null) {
            u.setPic3(filePath + u.getPic3());
        }
        if (u.getVideo() != null) {
            u.setVideo(filePath + u.getVideo());
        }
        if (u.getAudio() != null) {
            u.setAudio(filePath + u.getAudio());
        }
        return u;
    }

    //current user collection
    @GetMapping("collectList")
    public Result list(HttpServletRequest request) {
        Integer userId = JwtUtils.getUserId(request);
        LambdaQueryWrapper<FavorRecords> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FavorRecords::getUserId, userId);

        List<FavorRecords> favorRecords = favorRecordsMapper.selectList(wrapper);

        List<Knowledge> list = new ArrayList<>();

        favorRecords.forEach(u -> {
            Knowledge byId = knowledgeService.getById(u.getKnowledgeId());
            list.add(byId);
        });
        list.forEach(u -> {
            Knowledge knowledge = setCoverUrl(u);
            BeanUtil.copyProperties(knowledge, u);
            ;
        });

        return Result.success(list);
    }


}

