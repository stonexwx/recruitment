package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.JobSeeker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.dto.JobSeekerDTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Entity com.recruitment.dao.domain.JobSeeker
 */
public interface JobSeekerMapper extends BaseMapper<JobSeeker> {
    /**
     * 根据用户id查询求职者信息
     * @param uid
     * @return
     */
    List<JobSeekerDTO> seekerSelect(long uid);
}




