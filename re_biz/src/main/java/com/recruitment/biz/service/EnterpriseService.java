package com.recruitment.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.dto.EnterpriseAdminDTO;

import java.util.List;
import java.util.Map;


public interface EnterpriseService extends IService<Enterprise> {
    /**
     * 通过招聘信息获取企业信息
     * @param rid
     * @param type
     * @return
     */
    Enterprise selectByRid(Long rid,String type);

    /**
     * 企业排名查询
     * @return
     */
    List<Enterprise> selectByScale();

    /*
    管理员分隔线————————————————————————————————————————————————————————————————————————————————
     */
    /**
     * 管理员添加企业
     */
    void insertEnterpriseAdmin(Enterprise enterprise);

    /**
     * 管理员查询所有企业信息
     * @return
     */
    Map<String,Object> selectEnterpriseAdmin(int page, int limit);

    /**
     * 管理员更新企业信息
     * @param enterprise
     */
    void updateEnterpriseAdmin(EnterpriseAdminDTO enterprise);

    /**
     * 管理员删除企业信息
     */
    void deleteEnterpriseAdmin(Long eid);
}
