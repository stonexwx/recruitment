package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.ReInfo;
import com.recruitment.biz.service.ReInfoService;
import com.recruitment.dao.mapper.ReInfoMapper;
import com.recruitment.dao.dto.ReinfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("reInfoService")
public class ReInfoServiceImpl extends ServiceImpl<ReInfoMapper, ReInfo>
    implements ReInfoService{
    @Autowired
    ReInfoMapper reInfoMapper;

    /**
     * 不带条件全部查询
     * @return
     */
    @Override
    public List<ReinfoDTO> selectALL() {
        return reInfoMapper.selectAll("","",null);
    }


    /**
     * id查询
     */
    @Override
    public List<ReinfoDTO> selectALLById(Long rid) {
        return reInfoMapper.selectAll("","",rid);
    }
}




