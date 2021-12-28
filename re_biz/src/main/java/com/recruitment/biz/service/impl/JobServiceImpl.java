package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Job;
import com.recruitment.biz.service.JobService;
import com.recruitment.dao.mapper.JobMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job>
    implements JobService{

}




