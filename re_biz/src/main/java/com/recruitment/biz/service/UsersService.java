package com.recruitment.biz.service;

import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
