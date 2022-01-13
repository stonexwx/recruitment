package com.recruitment.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.domain.Interview;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Entity com.recruitment.dao.domain.Interview
 */
public interface InterviewMapper extends BaseMapper<Interview> {
    /**
    * 预约面试
     * @param date
     * @param rid
     * @param uid
     * @param state
     * @param addtime
     */
    void insertInterview(@Param("date")Date date,@Param("rid") Long rid,@Param("uid") Long uid
    ,@Param("state") String state,@Param("addtime") Date addtime);
}




