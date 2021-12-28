package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Users;
import com.recruitment.biz.service.UsersService;
import com.recruitment.dao.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




