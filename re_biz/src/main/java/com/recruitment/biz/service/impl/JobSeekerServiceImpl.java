package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.biz.service.JobSeekerService;
import com.recruitment.dao.dto.JobSeekerDTO;
import com.recruitment.dao.mapper.JobSeekerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class JobSeekerServiceImpl extends ServiceImpl<JobSeekerMapper, JobSeeker>
    implements JobSeekerService{
    @Autowired
    JobSeekerMapper jobSeekerMapper;

    /**
     * 根据id查询求职者信息
     * @param uid
     * @return
     */
    @Override
    public List<JobSeekerDTO> seekerSelect(long uid) {
        return jobSeekerMapper.seekerSelect(uid);
    }
}




