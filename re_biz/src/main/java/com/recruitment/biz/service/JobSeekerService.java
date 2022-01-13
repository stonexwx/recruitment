package com.recruitment.biz.service;

import com.recruitment.dao.domain.JobSeeker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.domain.Users;
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

    /**
     * 求职者普通信息修改
     * @param jobSeeker
     * @return
     */
    boolean seekerUpdate(JobSeekerDTO jobSeeker);

    /**
     * 求职者文件上传修改
     */
    boolean seekerFileUpdate(String type, String address, Users users);
}
