package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Token;
import com.recruitment.biz.service.TokenService;
import com.recruitment.dao.mapper.TokenMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token>
    implements TokenService{

}




