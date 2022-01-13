package com.recruitment.biz.service.impl;

import com.recruitment.biz.service.Query;
import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.domain.ReInfo;
import com.recruitment.dao.dto.ReinfoDTO;
import com.recruitment.dao.mapper.EnterpriseMapper;
import com.recruitment.dao.mapper.ReInfoMapper;
import com.recruitment.global.QueryAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<QueryAll> select(String message, String se_type, String type) {

        List<QueryAll> list = new ArrayList<>();
        if(se_type.equals("1")){
           for(Enterprise enterprise: enterpriseMapper.selectAllByEnameAndEtype(message,type)){
               QueryAll queryAll = new QueryAll();
               queryAll.setName(enterprise.getEname());
               queryAll.setAddress(enterprise.getAddress());
               queryAll.setOther(enterprise.getE_email());
               queryAll.setEid(enterprise.getEid());
               list.add(queryAll);
           }
           return list;
        }else{
            for(ReinfoDTO reInfo :reInfoMapper.selectAll(message,type,null)) {
                QueryAll queryAll = new QueryAll();
                queryAll.setName(reInfo.getReInfo().getJob_name());
                queryAll.setAddress(reInfo.getReInfo().getFull_path());
                queryAll.setOther(String.valueOf(reInfo.getReInfo().getScale()));
                queryAll.setRid(reInfo.getReInfo().getRid());
                list.add(queryAll);
            }
        }
        return list;
    }
}
