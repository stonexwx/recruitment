package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 评价表
 * @TableName evaluation
 */
@TableName(value ="evaluation")
public class Evaluation extends Object implements Serializable {
    /**
     * 评价id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 企业id
     */
    @TableField(value = "eid")
    private Long eid;

    /**
     * 评价内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 评分
     */
    @TableField(value = "score")
    private String score;

    /**
     * 评价时间
     */
    @TableField(value = "addtime")
    private Date addtime;

    private Long uid;
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 评价id
     */
    public Long getId() {
        return id;
    }

    /**
     * 评价id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业id
     */
    public Long getEid() {
        return eid;
    }

    /**
     * 企业id
     */
    public void setEid(Long eid) {
        this.eid = eid;
    }

    /**
     * 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评价内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 评分
     */
    public String getScore() {
        return score;
    }

    /**
     * 评分
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 评价时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 评价时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", eid=" + eid +
                ", content='" + content + '\'' +
                ", score='" + score + '\'' +
                ", addtime=" + addtime +
                '}';
    }
}