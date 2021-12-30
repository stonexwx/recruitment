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
    @Override
    public List<ReinfoDTO> selectALL() {
        return reInfoMapper.selectAll();
    }

    @Override
    public List<ReInfo> selecChose(String type, String path) {
        Map<String,String> map = new HashMap<>();
        map.put("type",type);
        map.put("path",path);
        return reInfoMapper.selectChose(map);
    }
}




