package com.recruitment.biz.service.impl;

import com.recruitment.biz.service.Query;
import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.mapper.EnterpriseMapper;
import com.recruitment.dao.mapper.ReInfoMapper;
import com.recruitment.global.QueryAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Queryimpl implements Query {
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    ReInfoMapper reInfoMapper;
    /**
     * 综合查询
     * @param message
     * @param se_type
     * @param type
     * @return
     */
    @Override
    public QueryAll select(String message, String se_type, String type) {
        QueryAll queryAll = new QueryAll();
        if(se_type.equals("1")){
           queryAll.setEnterprise(enterpriseMapper.selectAllByEnameAndEtype(message,type));
           return queryAll;
        }else{
            queryAll.setReInfo(reInfoMapper.selectAll(message,type));
        }
        return queryAll;
    }
}
