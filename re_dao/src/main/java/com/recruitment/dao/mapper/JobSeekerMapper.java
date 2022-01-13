package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.JobSeeker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.dto.JobSeekerDTO;
import com.recruitment.dao.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.recruitment.dao.domain.JobSeeker
 */
public interface JobSeekerMapper extends BaseMapper<JobSeeker> {
    /**
     * 根据用户id查询求职者信息
     * @param uid
     * @return
     */
    JobSeekerDTO seekerSelect(long uid);
    /**
     * 应聘者信息更新
     * @param jobSeeker
     */
    void updateAll(JobSeekerDTO jobSeeker);

    /**
     * 招聘者文件更新
     */
    void updatePhotoAndEducationAndResume(@Param("address") String address,@Param("type") String type,@Param("uid") Long uid);
    /**
     * 招聘者注册
     */
    boolean insertUid(@Param("userDTO")UserDTO userDTO);
}




