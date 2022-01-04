package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.dto.UserDTO;
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
    /**
     * 注册用户
     */
    boolean insertAll(@Param("userDTO") UserDTO userDTO);
    /**
     * 查询用户id
     */
    Users selectPhone(@Param("userDTO")UserDTO userDTO);
    /**
     * 验证手机号
     */
    String selectPhoneByPhone(@Param("phone") String phone);
}




