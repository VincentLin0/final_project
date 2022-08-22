package com.mastercs.demo.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mastercs.demo.bean.Knowledge;
import com.mastercs.demo.mapper.KnowledgeMapper;
import com.mastercs.demo.services.KnowledgeService;
import org.springframework.stereotype.Service;


@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeService {

}
