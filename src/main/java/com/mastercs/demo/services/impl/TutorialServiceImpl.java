package com.mastercs.demo.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mastercs.demo.bean.Tutorial;
import com.mastercs.demo.mapper.TutorialMapper;
import com.mastercs.demo.services.TutorialService;
import org.springframework.stereotype.Service;


@Service
public class TutorialServiceImpl extends ServiceImpl<TutorialMapper, Tutorial> implements TutorialService {

}
