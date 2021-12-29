package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Token;
import com.recruitment.dao.domain.Users;
import com.recruitment.biz.service.UsersService;
import com.recruitment.dao.mapper.ReInfoMapper;
import com.recruitment.dao.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{
    @Autowired
    UsersMapper usersMapper;
    @Override
    public Boolean login(String phone, String password,String session) {
        Users users =new Users();
        users.setPhone(phone);
        users.setPassword(password);
        Boolean flag =false;
        if(usersMapper.login(users) !=null){
           flag=true;
        }
        return flag;
    }


}




