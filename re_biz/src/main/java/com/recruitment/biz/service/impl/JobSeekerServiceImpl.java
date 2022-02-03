package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.biz.service.JobSeekerService;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.JobSeekerDTO;
import com.recruitment.dao.mapper.JobSeekerMapper;
import com.recruitment.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public JobSeekerDTO seekerSelect(long uid) {
        return jobSeekerMapper.seekerSelect(uid);
    }

    /**
     * 根据id修改数据
     *
     * @param jobSeeker@return
     */
    @Override
    public boolean seekerUpdate(JobSeekerDTO jobSeeker) {
        Contant contant =new Contant();
        jobSeeker.getJobSeeker().setJob_type(contant.getPost2M().get(jobSeeker.getJobName()));
        jobSeekerMapper.updateAll(jobSeeker);
        return true;
    }

    /**
     * 文件上传
     * @param type
     * @param address
     * @param users
     * @return
     */
    @Override
    public boolean seekerFileUpdate(String type, String address, Users users) {
        jobSeekerMapper.updatePhotoAndEducationAndResume(address,type, users.getUid());
        return true;
    }

}




