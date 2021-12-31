package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.JobSeekerServiceImpl;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.JobSeekerDTO;
import com.recruitment.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SeekerConller {
    @Autowired
    JobSeekerServiceImpl jobSeekerService;

    /**
     * @return
     */
    @RequestMapping("/seeker_select")
    @ResponseBody
    public String seekerSelect(HttpSession httpSession) {
        Users users = (Users) httpSession.getAttribute("user");
        JobSeekerDTO jobSeekerDTO = jobSeekerService.seekerSelect(users.getUid());
        Map<String, Object> map = new HashMap<>();
        if (jobSeekerDTO != null) {
            if (jobSeekerDTO.getJobSeeker().getSex().equals("1")) {
                jobSeekerDTO.getJobSeeker().setSex("女");
            } else {
                jobSeekerDTO.getJobSeeker().setSex("男");
            }
            map.put("flag", "true");
            map.put("jobSeeker", jobSeekerDTO);
            httpSession.setAttribute("jobSeeker", jobSeekerDTO);
            return JSON.toJSONString(map);
        }
        map.put("flag", "false");
        map.put("jobSeeker", "null");
        return JSON.toJSONString(map);
    }

    /**
     * 更新用户信息
     */
    @RequestMapping("/seeker_update")
    @ResponseBody
    public String updateSeeker(String name, String username, String education, String sex, String email, String job_type, HttpSession httpSession) {
        JobSeekerDTO jobSeekerDTO = (JobSeekerDTO) httpSession.getAttribute("jobSeeker");
        Users users = (Users) httpSession.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        if (!jobSeekerDTO.getJobSeeker().getName().equals(username)) {
            jobSeekerDTO.getJobSeeker().setName(username);
        }
        if (!users.getUser_name().equals(name)) {
            users.setUser_name(name);
        }
        if (!jobSeekerDTO.getJobSeeker().getEducation().equals(education)) {
            jobSeekerDTO.getJobSeeker().setEducation(education);
        }
        if (jobSeekerDTO.getJobSeeker().getSex().equals("女")) {
            jobSeekerDTO.getJobSeeker().setSex("1");
            if(!jobSeekerDTO.getJobSeeker().getSex().equals(sex)){
                jobSeekerDTO.getJobSeeker().setSex("0");
            }
        }
        if (jobSeekerDTO.getJobSeeker().getSex().equals("男")) {
            jobSeekerDTO.getJobSeeker().setSex("0");
            if(!jobSeekerDTO.getJobSeeker().getSex().equals(sex)){
                jobSeekerDTO.getJobSeeker().setSex("1");
            }
        }
        if(!jobSeekerDTO.getJobName().equals(job_type)){
            jobSeekerDTO.setJobName(job_type);
        }
        if (!jobSeekerDTO.getJobSeeker().getEmail().equals(email)){
            jobSeekerDTO.getJobSeeker().setEmail(email);
        }
        jobSeekerDTO.setUsers(users);
        if(jobSeekerService.seekerUpdate(jobSeekerDTO)){
            httpSession.setAttribute("user",users);
            httpSession.setAttribute("jobSeeker",jobSeekerDTO);
            map.put("flag","true");
            return JSON.toJSONString(map);
        }
        return JSON.toJSONString(map.put("flag","false"));
    }
}
