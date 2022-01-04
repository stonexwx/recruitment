package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.biz.service.UsersService;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.UserDTO;
import com.recruitment.dao.mapper.JobSeekerMapper;
import com.recruitment.dao.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("user")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
        implements UsersService {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    JobSeekerMapper jobSeekerMapper;

    /**
     * 登录验证
     *
     * @param phone
     * @param password
     * @return Users
     */
    @Override
    public Users login(String phone, String password) {
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(password);
        return usersMapper.login(users);
    }

    /**
     * 更新密码
     *
     * @param newPassword
     * @param password
     * @param users
     * @return boolean
     */
    @Override
    public boolean updatePassword(String newPassword, String password, Users users) {
        if (password.equals(users.getPassword())) {
            usersMapper.updatePassword(users, newPassword);
            return true;
        }
        return false;
    }

    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    @Override
    public boolean insertUser(UserDTO userDTO) {
        usersMapper.insertAll(userDTO);
        Users users = usersMapper.selectPhone(userDTO);
        userDTO.setUsers(users);
        return jobSeekerMapper.insertUid(userDTO);
    }

    /**
     * 查询手机号是否注册
     * @param phone
     * @return
     */
    @Override
    public String selectPhoneByPhone(String phone) {
        return usersMapper.selectPhoneByPhone(phone);
    }
}




