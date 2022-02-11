package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.EnterpriseService;
import com.recruitment.biz.service.impl.EnterpriseServiceImpl;
import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.dto.EnterpriseAdminDTO;
import com.recruitment.dao.dto.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class EnterpriseController {
    EnterpriseService enterpriseServiceImpl;
    @Autowired
    public EnterpriseController(EnterpriseService enterpriseServiceImpl) {
        this.enterpriseServiceImpl = enterpriseServiceImpl;
    }

    /**
     * 根据招聘信息查询企业信息
     *
     * @param rid
     * @return
     */
    @RequestMapping("/enterprise_select")
    @ResponseBody
    public String selectByRid(String rid) {
        String type;
        String a;
        if (rid.contains("e")) {
            a=rid.substring(1);
            type="teshu";
        }else {
            a=rid;
            type="putong";
        }
        Enterprise enterprise = enterpriseServiceImpl.selectByRid(Long.valueOf(a), type);
        return JSON.toJSONString(enterprise);
    }

    /**
     * 企业排名查询
     *
     * @return
     */
    @RequestMapping("/enterprise_paiming_select")
    @ResponseBody
    public String selectByScale() {
        List<Enterprise> list = enterpriseServiceImpl.selectByScale();
        Map<String, Object> map = new HashMap<>();

        List<EnterpriseDTO> list1 = new ArrayList<>();
        int i = 0;
        for (Enterprise enterprise : list) {
            i++;
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setEnterprise(enterprise);
            enterpriseDTO.setNumber(String.valueOf(i));
            list1.add(enterpriseDTO);
            map.put("data", list1);
        }
        return JSON.toJSONString(map);
    }
    /*
      管理员分界线--------------------------------------------------------------------------------------
     */


    /**
     * 管理员操作企业查询
     */
    @RequestMapping("/admin_enterprise_select")
    @ResponseBody
    public String enterpriseSelectAdmin(int page, int limit,String deptNo,String deptName) {
        return JSON.toJSONString(enterpriseServiceImpl.selectEnterpriseAdmin(page, limit,deptNo , deptName));
    }

    /**
     * 管理员操作企业更改
     */
    @RequestMapping("/admin_enterprise_update")
    @ResponseBody
    public String enterpriseUpdateAdmin(EnterpriseAdminDTO enterpriseAdminDTO) {

        enterpriseServiceImpl.updateEnterpriseAdmin(enterpriseAdminDTO);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return JSON.toJSONString(map);
    }

    /**
     * 管理员操作企业删除
     */
    @ResponseBody
    @RequestMapping("/admin_enterprise_delete")
    public String enterpriseDeleteAdmin(Long eid) {
        enterpriseServiceImpl.deleteEnterpriseAdmin(eid);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return JSON.toJSONString(map);
    }

}
