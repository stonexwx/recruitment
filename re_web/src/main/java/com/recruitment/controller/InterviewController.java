package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.InterviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class InterviewController {
    @Autowired
    InterviewServiceImpl interviewService;
    @RequestMapping("/intereview_insert")
    @ResponseBody
    public String insertInterface(String date, Long rid, Long sid, String state) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        interviewService.insertInterview(format.parse(date),rid,sid,state);
        Map<String,Boolean> map=new HashMap<>();
        map.put("flag",true);
        return JSON.toJSONString(map);
    }
}
