package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.biz.service.EnterpriseService;
import com.recruitment.dao.domain.Enterprise;

import com.recruitment.dao.dto.EnterpriseAdminDTO;
import com.recruitment.dao.mapper.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service()
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService {
    @Autowired
    EnterpriseMapper enterpriseMapper;

    /**
     * 通过招聘信息获取企业信息
     * @param rid
     * @return
     */
    @Override
    public Enterprise selectByRid(Long rid,String type) {
        return enterpriseMapper.selectAllByRid(rid,type);
    }

    /**
     * 根据得分排序获得
     * @return
     */
    @Override
    public List<Enterprise> selectByScale() {
        return enterpriseMapper.selectByEvaluation();
    }

    /*
    我是分隔线-----------------------------------------------------------------------------------
     */

    /**
     * 管理员增加企业
     * @param enterprise
     *
     */
    @Override
    public void insertEnterpriseAdmin(Enterprise enterprise) {
        enterpriseMapper.insertAll(enterprise);
    }

    /**
     * 管理员查询所有企业信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Map<String, Object> selectEnterpriseAdmin(int page, int limit) {
        Map<String,Object> map = new HashMap<>();
        map.put("data",enterpriseMapper.selectAll(((page-1)*10),limit));
        map.put("flag",true);
        map.put("count",enterpriseMapper.count());
        return map;
    }

    /**
     * 管理员更新企业信息
     *
     * @param enterprise
     */
    @Override
    public void updateEnterpriseAdmin(EnterpriseAdminDTO enterprise) {
        enterpriseMapper.updateAll(enterprise);
    }
    /**
     * 管理员删除企业信息
     */
    /**
     * 管理员删除企业信息
     *
     * @param eid
     */
    @Override
    public void deleteEnterpriseAdmin(Long eid) {
        enterpriseMapper.deleteByEid(eid);
    }
}




