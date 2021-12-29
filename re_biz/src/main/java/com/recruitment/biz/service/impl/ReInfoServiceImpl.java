package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.ReInfo;
import com.recruitment.biz.service.ReInfoService;
import com.recruitment.dao.mapper.ReInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("reInfoService")
public class ReInfoServiceImpl extends ServiceImpl<ReInfoMapper, ReInfo>
    implements ReInfoService{
    @Autowired
    ReInfoMapper reInfoMapper;
    @Override
    public List<ReInfo> selectALL() {
        return reInfoMapper.selectAll();
    }
}




