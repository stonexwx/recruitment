package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.biz.service.UsersService;
import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.UserAdminDTO;
import com.recruitment.dao.dto.UserDTO;
import com.recruitment.dao.mapper.JobSeekerMapper;
import com.recruitment.dao.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return jobSeekerMapper.updateUid(userDTO);
    }

    /**
     * 查询手机号是否注册
     *
     * @param phone
     * @return
     */
    @Override
    public String selectPhoneByPhone(String phone) {
        return usersMapper.selectPhoneByPhone(phone);
    }
/*-----------------------我是分割线-------------------------------------------------*/
    /**
     * 管理员查询所有用户
     * @return
     * @param n
     * @param page
     * @param userName
     * @param userMobile
     */
    @Override
    public Map<String, Object> userSelectAll(int n, int page, String userName, String userMobile) {
        List<UserAdminDTO> list= usersMapper.selectall((n-1)*page,page, userName,userMobile );
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("flag",true);
        map.put("count",usersMapper.selectContent());
        return map;
    }

    /**
     * 管理员更改用户
     *
     * @param users
     */
    @Override
    public void userUpdate(UserAdminDTO users) {
        if(users.getUid()==null){
            UserDTO userDTO = new UserDTO();
            Users users1=new Users();
            JobSeeker jobSeeker = new JobSeeker();
            users1.setUser_name(users.getUserName());
            users1.setPhone(users.getUserMobile());
            users1.setPassword("000000");
            Date date =new Date();
            users1.setAddtime(date);
            jobSeeker.setEmail(users.getUserEmail());
            jobSeeker.setSex(users.getUserSex());
            jobSeeker.setName(users.getUserRealName());
            userDTO.setUsers(users1);
            userDTO.setJobSeeker(jobSeeker);
            usersMapper.insertAll(userDTO);
            userDTO.getUsers().setUid(usersMapper.selectPhone(userDTO).getUid());
            jobSeekerMapper.updateUid(userDTO);
        }
        usersMapper.updateUserForAdmin(users);
    }

    /**
     * 管理员删除用户
     *
     * @param uid
     */
    @Override
    public void userDelete(Long uid) {
        usersMapper.deleteByUidAll(uid);
    }

    /**
     * 重置密码
     *
     * @param uid
     */
    @Override
    public void passwordInfo(Long uid) {
        usersMapper.updatePasswordAndPassword(uid);
    }
}




