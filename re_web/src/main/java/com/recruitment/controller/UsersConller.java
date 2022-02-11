package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.UsersService;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.UserAdminDTO;
import com.recruitment.dao.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8081/")
public class UsersConller {
    UsersService usersServiceImpl;
    @Autowired
    public UsersConller(UsersService usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    /**
     * 更新密码
     *
     * @param newPassword
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping("/password_update")
    @ResponseBody
    public String updatePassword(String newPassword, String password, HttpSession httpSession) {
        Boolean flag = usersServiceImpl.updatePassword(newPassword, password, (Users) httpSession.getAttribute("user"));
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", flag);
        return JSON.toJSONString(map);
    }

    /**
     * 用户注册
     */
    @RequestMapping("/user_insert")
    @ResponseBody
    public String userInsert(String name, String phone, String password, String sex, String email) {
        UserDTO userDTO = new UserDTO();
        userDTO.getUsers().setUser_name(name);
        userDTO.getUsers().setPassword(password);
        userDTO.getUsers().setPhone(phone);
        if (sex.equals("女")) {
            userDTO.getJobSeeker().setSex("1");
        } else {
            userDTO.getJobSeeker().setSex("0");
        }
        Date date = new Date();
        userDTO.getUsers().setAddtime(date);
        userDTO.getJobSeeker().setAddtime(date);
        userDTO.getUsers().setRole_id(0L);
        userDTO.getJobSeeker().setEmail(email);
        Boolean flag = usersServiceImpl.insertUser(userDTO);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", flag);
        return JSON.toJSONString(map);
    }

    /**
     * 用户手机号检测
     *
     * @return
     */
    @RequestMapping("/phone")
    @ResponseBody
    public String selectPhone(String phone) {
        Map<String, Boolean> map = new HashMap<>();
        if(usersServiceImpl.selectPhoneByPhone(phone)!=null){
            map.put("flag", true);
            return JSON.toJSONString(map);
        }
        map.put("flag", false);
        return JSON.toJSONString(map);
    }
    /*管理员分隔线------------------------------------------------------------------------------------
     */
    /**
     * 管理员查询所有用户
     */
    @RequestMapping("/admin_user_select")
    @ResponseBody
    public String userSelectAdmin(int page,int limit,String userName,String userMobile){
        return JSON.toJSONString(usersServiceImpl.userSelectAll(page, limit,userName ,userMobile ));
    }
    /**
     * 管理员更改用户
     * @param users
     */
    @RequestMapping("/admin_user_update")
    @ResponseBody
    public String userUpdateAdmin(UserAdminDTO users){
        usersServiceImpl.userUpdate(users);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return JSON.toJSONString(map);
    }
    /**
     * 管理员删除用户
     */
    @RequestMapping("/admin_user_delete")
    @ResponseBody
    public String userDeleteAdmin(Long uid){
        usersServiceImpl.userDelete(uid);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return JSON.toJSONString(map);
    }
    /**
     * 重置密码
     */
    @RequestMapping("/password_info")
    @ResponseBody
    public String passwordInfo(Long uid){
        usersServiceImpl.passwordInfo(uid);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        return JSON.toJSONString(map);
    }
}
