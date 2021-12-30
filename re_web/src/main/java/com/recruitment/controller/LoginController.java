package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.UsersServiceImpl;
import com.recruitment.dao.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    public String login(String phone, String password, ModelMap modelMap){
        Users users=usersService.login(phone,password);
        if(users.getUid()!=null){
            modelMap.addAttribute("user",users);
            return "";
        }
        return "";
    }
    @RequestMapping("/loginData")
    @ResponseBody
    public String loginData(ModelMap modelMap){
        Users users = (Users) modelMap.get("user");
        String jsonUser = JSON.toJSONString(users);
        return jsonUser;
    }
}
