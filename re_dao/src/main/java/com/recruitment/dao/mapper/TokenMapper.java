package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.Token;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.recruitment.dao.domain.Token
 */
public interface TokenMapper extends BaseMapper<Token> {
    void insertToken(Token token);
}




