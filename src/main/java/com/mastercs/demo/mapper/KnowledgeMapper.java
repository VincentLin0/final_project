package com.mastercs.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mastercs.demo.bean.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface KnowledgeMapper extends BaseMapper<Knowledge> {

}
