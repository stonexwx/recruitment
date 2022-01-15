package com.recruitment.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.domain.Interview;
import com.recruitment.dao.dto.InterviewDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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
    /**
     * 查询
     * @param n
     * @param page
     */
    List<InterviewDTO> selectInterview(@Param("n") int n,@Param("page") int page);
    /**
     * 查询总条数
     */
    int selectCount();
    /**
     * 根据id删除面试信息
     */
    void deleteByIid(@Param("iid") Long iid);
}




