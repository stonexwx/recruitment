package com.recruitment.biz.service;

import com.recruitment.dao.domain.Interview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

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
}
