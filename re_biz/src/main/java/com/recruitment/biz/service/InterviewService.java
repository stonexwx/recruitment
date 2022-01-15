package com.recruitment.biz.service;

import com.recruitment.dao.domain.Interview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.Map;

/**
 *
 */
public interface InterviewService extends IService<Interview> {
    /**
     * 录入面试信息
     * @param date
     * @param rid
     * @param sid
     * @param state
     */
    void insertInterview(Date date, Long rid, Long sid
            , String state);
    /**
     * 查询全部
     * @return
     * @param n
     * @param page
     */
    Map<String,Object> selectInterview(int n,int page);
    /**
     * 删除
     */
    void deleteInterview(Long iid);
}
