package com.mastercs.demo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
@Data
@Table(name = "favor_records")//数据库表名
public class FavorRecords  implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer knowledgeId;
    private Integer userId;
}
