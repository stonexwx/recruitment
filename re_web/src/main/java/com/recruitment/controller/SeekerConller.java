package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.Job2Service;
import com.recruitment.biz.service.JobSeekerService;
import com.recruitment.biz.service.impl.Job2ServiceImpl;
import com.recruitment.dao.domain.Job2;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.JobSeekerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SeekerConller {
    JobSeekerService jobSeekerServiceImpl;
    Job2Service job2ServiceImpl;
    @Autowired
    public SeekerConller(JobSeekerService jobSeekerServiceImpl, Job2Service job2ServiceImpl) {
        this.jobSeekerServiceImpl = jobSeekerServiceImpl;
        this.job2ServiceImpl = job2ServiceImpl;
    }

    /**
     * 查询求职这信息
     * @return
     */
    @RequestMapping("/seeker_select")
    @ResponseBody
    public String seekerSelect(HttpSession httpSession) {
        Users users = (Users) httpSession.getAttribute("user");
        JobSeekerDTO jobSeekerDTO = jobSeekerServiceImpl.seekerSelect(users.getUid());
        Map<String, Object> map = new HashMap<>();
        if (jobSeekerDTO != null) {
            if (jobSeekerDTO.getJobSeeker().getSex().equals("1")) {
                jobSeekerDTO.getJobSeeker().setSex("女");
            } else {
                jobSeekerDTO.getJobSeeker().setSex("男");
            }
            map.put("flag", "true");
            map.put("jobSeekerDTO", jobSeekerDTO);
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
    public String updateSeeker(String name, String education, String sex, String email, String job_type, HttpSession httpSession) {
        JobSeekerDTO jobSeekerDTO = (JobSeekerDTO) httpSession.getAttribute("jobSeeker");
        Users users = (Users) httpSession.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        if (!jobSeekerDTO.getJobSeeker().getName().equals(name)||jobSeekerDTO.getJobSeeker().getName()==null) {
            jobSeekerDTO.getJobSeeker().setName(name);
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
        if(jobSeekerServiceImpl.seekerUpdate(jobSeekerDTO)){
            httpSession.setAttribute("user",users);
            httpSession.setAttribute("jobSeeker",jobSeekerDTO);
            map.put("flag","true");
            return JSON.toJSONString(map);
        }
        return JSON.toJSONString(map.put("flag","false"));
    }
    @ResponseBody
    @RequestMapping("/job_type")
    public String jobType(){
       List<Job2> job2= job2ServiceImpl.selectAll();
       return JSON.toJSONString(job2);
    }
}
