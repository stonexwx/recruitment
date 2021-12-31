package com.recruitment.biz.service;

import com.recruitment.dao.domain.JobSeeker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.dto.JobSeekerDTO;

import java.util.List;

/**
 *
 */
public interface JobSeekerService extends IService<JobSeeker> {
    /**
     * 根据用户id获取，招聘者信息
     * @param uid
     * @return
     */
    JobSeekerDTO seekerSelect(long uid);

    boolean seekerUpdate(JobSeekerDTO jobSeeker);
}
