package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.JobSeekerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeekerConller {
    @Autowired
    JobSeekerServiceImpl jobSeekerService;
    @RequestMapping("/seeker_select")
    @ResponseBody
    public String seekerSelect(String uid){
        return JSON.toJSONString(jobSeekerService.seekerSelect(Integer.parseInt(uid)));
    }
}
