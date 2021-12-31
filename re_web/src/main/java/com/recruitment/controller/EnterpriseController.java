package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.EnterpriseServiceImpl;
import com.recruitment.dao.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseServiceImpl enterpriseService;
    @RequestMapping("/enterprise_select")
    @ResponseBody
    public String selectByRid(String rid){
       Enterprise enterprise = enterpriseService.selectByRid(Long.valueOf(rid));
       return JSON.toJSONString(enterprise);
    }
}
