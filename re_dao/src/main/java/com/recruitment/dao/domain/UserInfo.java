package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @TableName user_info
 */
@TableName(value ="user_info")
public class UserInfo extends Object implements Serializable {
    /**
     * 用户信息id
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private Long u_id;

    /**
     * 
     */
    @TableField(value = "addtime")
    private Date addtime;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Long uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户信息id
     */
    public Long getU_id() {
        return u_id;
    }

    /**
     * 用户信息id
     */
    public void setU_id(Long u_id) {
        this.u_id = u_id;
    }

    /**
     * 
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }
}