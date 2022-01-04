package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.EnterpriseServiceImpl;
import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.dto.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseServiceImpl enterpriseService;

    /**
     * 根据招聘信息查询企业信息
     * @param rid
     * @return
     */
    @RequestMapping("/enterprise_select")
    @ResponseBody
    public String selectByRid(String rid){
       Enterprise enterprise = enterpriseService.selectByRid(Long.valueOf(rid));
       return JSON.toJSONString(enterprise);
    }

    /**
     * 企业排名查询
     * @return
     */
    @RequestMapping("/enterprise_paiming_select")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8081/")
    public String selectByScale(){
        List<Enterprise> list = enterpriseService.selectByScale();
        Map<String,Object> map = new HashMap<>();

        List<EnterpriseDTO> list1 =new ArrayList<>();
        int i=0;
        for (Enterprise enterprise:list){
            i++;
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setEnterprise(enterprise);
            enterpriseDTO.setNumber(String.valueOf(i));
            list1.add(enterpriseDTO);
            map.put("data",list1);
        }
        return JSON.toJSONString(map);
    }
}
