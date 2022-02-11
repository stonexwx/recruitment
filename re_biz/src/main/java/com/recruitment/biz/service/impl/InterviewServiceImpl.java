package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Interview;
import com.recruitment.biz.service.InterviewService;
import com.recruitment.dao.dto.InterviewDTO;
import com.recruitment.dao.mapper.InterviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("interviewService")
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview>
    implements InterviewService{
    @Autowired
    InterviewMapper interviewMapper;

    /**
     * 插入面试信息
     * @param date
     * @param rid
     * @param uid
     * @param state
     */
    @Override
    public void insertInterview(Date date, Long rid, Long uid, String state) {
        Date addtime = new Date();
        interviewMapper.insertInterview(date,rid, uid,state,addtime);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public Map<String, Object> selectInterview(int n,int page) {
        List<InterviewDTO> list = interviewMapper.selectInterview((n-1)*page,page);
        Map<String,Object> map = new HashMap<>();
        map.put("interview",list);
        map.put("count",interviewMapper.selectCount());
        map.put("flag",true);
        return map;
    }

    /**
     * 删除
     *
     * @param iid
     */
    @Override
    public void deleteInterview(Long iid) {
        interviewMapper.deleteByIid(iid);
    }
}




