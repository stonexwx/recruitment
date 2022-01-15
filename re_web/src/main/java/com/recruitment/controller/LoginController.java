package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.UsersServiceImpl;
import com.recruitment.dao.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class LoginController {
    @Autowired
    UsersServiceImpl usersService;

    /**
     * 登录验证
     * @param phone
     * @param password
     * @return 网址
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String phone, String password, HttpSession httpSession){
        Users users=usersService.login(phone,password);
        Map<String,Object> map = new HashMap<>();
        if(users!=null){
            httpSession.setAttribute("user",users);
            map.put("flag","true");
            map.put("user",users);
            return JSON.toJSONString(map);
        }
        map.put("flag",false);
        return JSON.toJSONString(map);
    }

}
