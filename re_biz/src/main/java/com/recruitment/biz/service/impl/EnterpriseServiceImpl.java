package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Enterprise;
import com.recruitment.biz.service.EnterpriseService;
import com.recruitment.dao.mapper.EnterpriseMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise>
    implements EnterpriseService{

}



