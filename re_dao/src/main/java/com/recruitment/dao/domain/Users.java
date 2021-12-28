package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName users
 */
@TableName(value ="users")
public class Users extends Object implements Serializable {
    /**
     * 
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String user_name;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 角色
     */
    @TableField(value = "role_id")
    private Long role_id;

    /**
     * 新增时间
     */
    @TableField(value = "addtime")
    private Date addtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 用户名
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * 用户名
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 角色
     */
    public Long getRole_id() {
        return role_id;
    }

    /**
     * 角色
     */
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    /**
     * 新增时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 新增时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}