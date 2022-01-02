package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.UsersServiceImpl;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersConller {
    @Autowired
    UsersServiceImpl usersService;

    /**
     * 更新密码
     * @param newPassword
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping("/password_update")
    @ResponseBody
    public String updatePassword(String newPassword, String password, HttpSession httpSession){
        Boolean flag = usersService.updatePassword(newPassword,password, (Users) httpSession.getAttribute("user"));
        Map<String, Boolean> map =new HashMap<>();
        map.put("flag",flag);
        return JSON.toJSONString(map);
    }
    /**
     * 用户注册
     */
    @RequestMapping("/user_insert")
    @ResponseBody
    public String userInsert(String username,String phone,String password,String sex,String email){
        UserDTO userDTO = new UserDTO();
        userDTO.getUsers().setUser_name(username);
        userDTO.getUsers().setPassword(password);
        userDTO.getUsers().setPhone(phone);
        if (sex.equals("女")) {
            userDTO.getJobSeeker().setSex("1");
        } else {
            userDTO.getJobSeeker().setSex("0");
        }
        Date date =new Date();
        userDTO.getUsers().setAddtime(date);
        userDTO.getJobSeeker().setAddtime(date);
        userDTO.getUsers().setRole_id(0L);
        userDTO.getJobSeeker().setEmail(email);
        Boolean flag = usersService.insertUser(userDTO);
        Map<String,Boolean> map =new HashMap<>();
        map.put("flag",flag);
        return JSON.toJSONString(map);
    }
}
