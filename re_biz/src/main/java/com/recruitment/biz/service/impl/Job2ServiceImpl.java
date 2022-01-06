package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Job2;
import com.recruitment.biz.service.Job2Service;
import com.recruitment.dao.mapper.Job2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class Job2ServiceImpl extends ServiceImpl<Job2Mapper, Job2>
    implements Job2Service{
    @Autowired
    Job2Mapper job2Mapper;
    @Override
    public List<Job2> selectAll() {

        return job2Mapper.selectAll();
    }
}




