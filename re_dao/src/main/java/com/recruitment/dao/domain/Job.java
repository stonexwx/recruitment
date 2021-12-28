package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 岗位类别表
 * @TableName job
 */
@TableName(value ="job")
public class Job extends Object implements Serializable {
    /**
     * 岗位id
     */
    @TableId(value = "jid", type = IdType.AUTO)
    private Long jid;

    /**
     * 岗位名称
     */
    @TableField(value = "job_name")
    private String job_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 岗位id
     */
    public Long getJid() {
        return jid;
    }

    /**
     * 岗位id
     */
    public void setJid(Long jid) {
        this.jid = jid;
    }

    /**
     * 岗位名称
     */
    public String getJob_name() {
        return job_name;
    }

    /**
     * 岗位名称
     */
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
}