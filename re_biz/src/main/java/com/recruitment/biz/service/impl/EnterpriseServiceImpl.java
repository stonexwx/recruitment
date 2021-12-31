package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.biz.service.EnterpriseService;
import com.recruitment.dao.domain.Enterprise;

import com.recruitment.dao.mapper.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Enterprise selectByRid(Long rid) {
        return enterpriseMapper.selectAllByRid(rid);
    }

    /**
     * 根据得分排序获得
     * @return
     */
    @Override
    public List<Enterprise> selectByScale() {
        return enterpriseMapper.selectByEvaluation();
    }
}




