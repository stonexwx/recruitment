package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 面试表
 * @TableName interview
 */
@TableName(value ="interview")
public class Interview extends Object implements Serializable {
    /**
     * 面试id
     */
    @TableId(value = "iid", type = IdType.AUTO)
    private Long iid;

    /**
     * 应聘者id
     */
    @TableField(value = "sid")
    private Long sid;

    /**
     * 招聘信息id
     */
    @TableField(value = "rid")
    private Long rid;

    /**
     * 预约时间
     */
    @TableField(value = "time")
    private Date time;

    /**
     * 添加时间
     */
    @TableField(value = "addtime")
    private Date addtime;

    /**
     * 预约状态
     */
    @TableField(value = "state")
    private String state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 面试id
     */
    public Long getIid() {
        return iid;
    }

    /**
     * 面试id
     */
    public void setIid(Long iid) {
        this.iid = iid;
    }

    /**
     * 应聘者id
     */
    public Long getSid() {
        return sid;
    }

    /**
     * 应聘者id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }

    /**
     * 招聘信息id
     */
    public Long getRid() {
        return rid;
    }

    /**
     * 招聘信息id
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    /**
     * 预约时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 预约时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 预约状态
     */
    public String getState() {
        return state;
    }

    /**
     * 预约状态
     */
    public void setState(String state) {
        this.state = state;
    }
}