package com.recruitment.biz.service;

import com.recruitment.dao.domain.JobSeeker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.dto.JobSeekerDTO;

import java.util.List;

/**
 *
 */
public interface JobSeekerService extends IService<JobSeeker> {
    List<JobSeekerDTO> seekerSelect(long uid);
}
