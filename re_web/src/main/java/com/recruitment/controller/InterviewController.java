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
import java.util.HashMap;
import java.util.Map;

@Controller
public class InterviewController {
    @Autowired
    InterviewServiceImpl interviewService;

    /**
     * 插入面试信息
     * @param value1
     * @param rid
     * @param httpSession
     * @return
     * @throws ParseException
     */
    @RequestMapping("/intereview_insert")
    @ResponseBody
    public String insertInterface(String value1, Long rid, HttpSession httpSession) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String state="0";
        Users users= (Users) httpSession.getAttribute("user");
        interviewService.insertInterview(format.parse(value1),rid,users.getUid(),state);
        return JSON.toJSONString(getMap(true));
    }

    private Map<String, Boolean> getMap(boolean flag) {
        Map<String,Boolean> map=new HashMap<>();
        map.put("flag",flag);
        return map;
    }

    /**
     * 审核面试信息
     */
    @RequestMapping("/interview_update")
    @ResponseBody
    public String interviewUpdate(){
        return null;
    }
    /**
     * 查询面试信息
     */
    @ResponseBody
    @RequestMapping("/interview_select")
    public String interviewSelect(int page,int limit){
        return JSON.toJSONString(interviewService.selectInterview(page,limit));
    }
    /**
     * 删除面试信息
     */
    @RequestMapping("/interview_delete")
    @ResponseBody
    public String interviewDelete(Long iid){
        interviewService.deleteInterview(iid);
        return JSON.toJSONString(getMap(true));
    }
}
