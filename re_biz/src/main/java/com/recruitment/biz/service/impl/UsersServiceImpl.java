package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Users;
import com.recruitment.biz.service.UsersService;
import com.recruitment.dao.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("user")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{
    @Autowired
    UsersMapper usersMapper;

    /**
     * 登录验证
     * @param phone
     * @param password
     * @return Users
     */
    @Override
    public Users login(String phone, String password) {
        Users users =new Users();
        users.setPhone(phone);
        users.setPassword(password);
        return usersMapper.login(users);
    }

    /**
     * 更新密码
     * @param newPassword
     * @param password
     * @param users
     * @return boolean
     */
    @Override
    public boolean updatePassword(String newPassword, String password, Users users) {
        if(password.equals(users.getPassword())){
            usersMapper.updatePassword(users,newPassword);
            return true;
        }
        return false;
    }


}




