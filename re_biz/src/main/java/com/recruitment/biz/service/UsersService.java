package com.recruitment.biz.service;

import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.dto.UserDTO;

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
}
