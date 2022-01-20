package com.recruitment.biz.service;

import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.dto.UserAdminDTO;
import com.recruitment.dao.dto.UserDTO;

import java.util.Map;

/**
 *
 */
public interface UsersService extends IService<Users> {
    /**
     * 登录验证
     * @return
     */
    Users login(String phone,String password);
    /**
     * 修改密码
     */
    boolean updatePassword(String newPassword,String password,Users users);
    /**
     * 注册用户
     */
    boolean insertUser(UserDTO userDTO);
    /**
     * 查询手机号是否注册
     * @return
     */
    String selectPhoneByPhone(String phone);
    /*管理员分隔线------------------------------------------------------------------------------------
     */
    /**
     * 管理员查询所有用户
     * @return
     * @param n
     * @param page
     * @param userName
     * @param userMobile
     */
    Map<String,Object> userSelectAll(int n, int page,String userName,String userMobile);
    /**
     * 管理员更改用户
     * @param users
     */
    void userUpdate(UserAdminDTO users);
    /**
     * 管理员删除用户
     */
    void userDelete(Long uid);
    /**
     * 重置密码
     */
    void passwordInfo(Long uid);
}
