package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 招聘信息
 * @TableName re_info
 */
@TableName(value ="re_info")
public class ReInfo extends Object implements Serializable {
    /**
     * 招聘信息id
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Long rid;

    /**
     * 企业id
     */
    @TableField(value = "eid")
    private Long eid;

    /**
     * 发布日期
     */
    @TableField(value = "addtime")
    private Date addtime;

    /**
     * 岗位名称
     */
    @TableField(value = "job_name")
    private String job_name;

    /**
     * 岗位类别
     */
    @TableField(value = "job_type")
    private Long job_type;

    /**
     * 二级岗位类别
     */
    @TableField(value = "job2_type")
    private Long job2_type;

    /**
     * 招聘人数
     */
    @TableField(value = "re_number")
    private String re_number;

    /**
     * 学历
     */
    @TableField(value = "re_education")
    private String re_education;

    /**
     * 岗位要求
     */
    @TableField(value = "requirement")
    private String requirement;

    /**
     * 工作地点
     */
    @TableField(value = "full_path")
    private String full_path;

    /**
     * 宣传标语
     */
    @TableField(value = "job_welfare")
    private String job_welfare;

    /**
     * 实习时间
     */
    @TableField(value = "job_time")
    private String job_time;

    /**
     * 工资
     */
    @TableField(value = "scale")
    private BigDecimal scale;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
     * 发布日期
     * @return
     */
    public String getAddtime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(addtime);
    }

    /**
     * 发布日期
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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

    /**
     * 岗位类别
     */
    public Long getJob_type() {
        return job_type;
    }

    /**
     * 岗位类别
     */
    public void setJob_type(Long job_type) {
        this.job_type = job_type;
    }

    /**
     * 二级岗位类别
     */
    public Long getJob2_type() {
        return job2_type;
    }

    /**
     * 二级岗位类别
     */
    public void setJob2_type(Long job2_type) {
        this.job2_type = job2_type;
    }

    /**
     * 招聘人数
     */
    public String getRe_number() {
        return re_number;
    }

    /**
     * 招聘人数
     */
    public void setRe_number(String re_number) {
        this.re_number = re_number;
    }

    /**
     * 学历
     */
    public String getRe_education() {
        return re_education;
    }

    /**
     * 学历
     */
    public void setRe_education(String re_education) {
        this.re_education = re_education;
    }

    /**
     * 岗位要求
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * 岗位要求
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * 工作地点
     */
    public String getFull_path() {
        return full_path;
    }

    /**
     * 工作地点
     */
    public void setFull_path(String full_path) {
        this.full_path = full_path;
    }

    /**
     * 宣传标语
     */
    public String getJob_welfare() {
        return job_welfare;
    }

    /**
     * 宣传标语
     */
    public void setJob_welfare(String job_welfare) {
        this.job_welfare = job_welfare;
    }

    /**
     * 实习时间
     */
    public String getJob_time() {
        return job_time;
    }

    /**
     * 实习时间
     */
    public void setJob_time(String job_time) {
        this.job_time = job_time;
    }

    /**
     * 工资
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 工资
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "ReInfo{" +
                "rid=" + rid +
                ", eid=" + eid +
                ", addtime=" + getAddtime() +
                ", job_name='" + job_name + '\'' +
                ", job_type=" + job_type +
                ", job2_type=" + job2_type +
                ", re_number='" + re_number + '\'' +
                ", re_education='" + re_education + '\'' +
                ", requirement='" + requirement + '\'' +
                ", full_path='" + full_path + '\'' +
                ", job_welfare='" + job_welfare + '\'' +
                ", job_time='" + job_time + '\'' +
                ", scale=" + scale +
                '}';
    }
}