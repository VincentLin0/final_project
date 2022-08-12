package com.mastercs.demo.dto;

// 用于映射数据库到后端，数据库：baidu_search，表名：items，映射过来叫：Search

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
public class KnowledgeDTO  {
    private Integer id;

    private String title;

    private String description;

    private String kind;

    private String pic1;

    private String pic2;

    private String pic3;

    private String audio;

    private String video;

    private Boolean isCollect;

}

