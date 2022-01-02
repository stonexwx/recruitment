package com.recruitment.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.domain.Enterprise;

import java.util.List;


public interface EnterpriseService extends IService<Enterprise> {
    /**
     * 通过招聘信息获取企业信息
     * @param rid
     * @return
     */
    Enterprise selectByRid(Long rid);

    List<Enterprise> selectByScale();

}
