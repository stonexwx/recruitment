package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 岗位二级表
 * @TableName job2
 */
@TableName(value ="job2")
public class Job2 extends Object implements Serializable {
    /**
     * 二级id
     */
    @TableId(value = "j_id", type = IdType.AUTO)
    private Long j_id;

    /**
     * 一级id
     */
    @TableField(value = "jid")
    private Long jid;

    /**
     * 二级名称
     */
    @TableField(value = "job2_name")
    private String job2_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 二级id
     */
    public Long getJ_id() {
        return j_id;
    }

    /**
     * 二级id
     */
    public void setJ_id(Long j_id) {
        this.j_id = j_id;
    }

    /**
     * 一级id
     */
    public Long getJid() {
        return jid;
    }

    /**
     * 一级id
     */
    public void setJid(Long jid) {
        this.jid = jid;
    }

    /**
     * 二级名称
     */
    public String getJob2_name() {
        return job2_name;
    }

    /**
     * 二级名称
     */
    public void setJob2_name(String job2_name) {
        this.job2_name = job2_name;
    }
}