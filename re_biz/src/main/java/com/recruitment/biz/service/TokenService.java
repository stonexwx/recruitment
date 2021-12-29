package com.recruitment.biz.service;

import com.recruitment.dao.domain.Token;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.domain.Users;

/**
 *
 */
public interface TokenService extends IService<Token> {
    void insertToken(String session, Users users);
}
