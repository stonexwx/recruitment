package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.recruitment.dao.domain.Users
 */
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 登录
     */
    Users login(Users users);
}




