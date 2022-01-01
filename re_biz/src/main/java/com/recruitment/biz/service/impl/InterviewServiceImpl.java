package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Interview;
import com.recruitment.biz.service.InterviewService;
import com.recruitment.dao.mapper.InterviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview>
    implements InterviewService{
    @Autowired
    InterviewMapper interviewMapper;
    @Override
    public void insertInterview(Date date, Long rid, Long sid, String state) {
        Date addtime = new Date();
        interviewMapper.insertInterview(date,rid, sid,state,addtime);
    }
}




