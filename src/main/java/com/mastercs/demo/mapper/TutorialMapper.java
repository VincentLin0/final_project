package com.mastercs.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mastercs.demo.bean.entity.Knowledge;
import com.mastercs.demo.bean.entity.Tutorial;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TutorialMapper extends BaseMapper<Tutorial> {

}
