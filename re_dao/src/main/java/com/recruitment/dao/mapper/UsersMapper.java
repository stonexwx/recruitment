package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.recruitment.dao.domain.Users
 */
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 登录验证
     */
    Users login(Users users);
    /**
     * 修改密码
     */
    void updatePassword(@Param("users") Users users,@Param("newPassword") String newPassword);
}




