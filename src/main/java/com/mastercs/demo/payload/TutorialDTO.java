package com.mastercs.demo.payload;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
public class TutorialDTO {
    private Integer id;

    private String title;

    private String description;

    private String video;

    private String absoluteVideo;

}

