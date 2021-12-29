package com.recruitment.biz.service;

import com.recruitment.dao.domain.Token;
import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UsersService extends IService<Users> {
    /**
     * 登录
     */
    Boolean login(String phone,String password,String session);
}
