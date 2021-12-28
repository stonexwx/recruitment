package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 角色
 * @TableName role
 */
@TableName(value ="role")
public class Role extends Object implements Serializable {
    /**
     * 
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long role_id;

    /**
     * 
     */
    @TableField(value = "role_name")
    private String role_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getRole_id() {
        return role_id;
    }

    /**
     * 
     */
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    /**
     * 
     */
    public String getRole_name() {
        return role_name;
    }

    /**
     * 
     */
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}