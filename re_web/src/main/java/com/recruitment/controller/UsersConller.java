package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.UsersServiceImpl;
import com.recruitment.dao.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

}
