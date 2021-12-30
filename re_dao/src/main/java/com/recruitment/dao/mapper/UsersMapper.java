package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.recruitment.dao.domain.Users
 */
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 登录验证
     */
    Users login(Users users);

}




