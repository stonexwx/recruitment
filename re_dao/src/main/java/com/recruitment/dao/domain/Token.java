package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName token
 */
@TableName(value ="token")
public class Token extends Object implements Serializable {
    /**
     * tokenid
     */
    @TableId(value = "tid")
    private Integer tid;

    /**
     * sessionid
     */
    @TableField(value = "session")
    private String session;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * tokenid
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * tokenid
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * sessionid
     */
    public String getSession() {
        return session;
    }

    /**
     * sessionid
     */
    public void setSession(String session) {
        this.session = session;
    }
}