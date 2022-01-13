package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.InterviewServiceImpl;
import com.recruitment.dao.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    public String insertInterface(String value1, Long rid, HttpSession httpSession) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String state="0";
        Users users= (Users) httpSession.getAttribute("user");
        interviewService.insertInterview(format.parse(value1),rid,users.getUid(),state);
        Map<String,Boolean> map=new HashMap<>();
        map.put("flag",true);
        return JSON.toJSONString(map);
    }
}
