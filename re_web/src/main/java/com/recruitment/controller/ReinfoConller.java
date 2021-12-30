package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.ReInfoServiceImpl;
import com.recruitment.dao.domain.ReInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReinfoConller {
    @Autowired
    ReInfoServiceImpl reInfoService;
    @RequestMapping("/reinfoAll")
    @ResponseBody
    public String reinfoSelect(){
        List<ReInfo> list =reInfoService.selectALL();
        String jsonUser = JSON.toJSONString(list);

        return jsonUser;
    }
}
