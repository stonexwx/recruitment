package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 求职者信息
 * @TableName job_seeker
 */
@TableName(value ="job_seeker")
public class JobSeeker extends Object implements Serializable {
    /**
     * 
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    /**
     * 
     */
    @TableField(value = "addtime")
    private Date addtime;

    /**
     * 头像
     */
    @TableField(value = "photo")
    private String photo;

    /**
     * 学历
     */
    @TableField(value = "education")
    private String education;

    /**
     * 岗位类别
     */
    @TableField(value = "job_type")
    private Long job_type;

    /**
     * 简历地址
     */
    @TableField(value = "resume")
    private String resume;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 学历信息图片
     */
    @TableField(value = "edu_phone")
    private String edu_phone;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getSid() {
        return sid;
    }

    /**
     * 
     */
    public void setSid(Long sid) {
        this.sid = sid;
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
     * 头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 学历
     */
    public void setEducation(String education) {
        this.education = education;
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
     * 简历地址
     */
    public String getResume() {
        return resume;
    }

    /**
     * 简历地址
     */
    public void setResume(String resume) {
        this.resume = resume;
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

    /**
     * 学历信息图片
     */
    public String getEdu_phone() {
        return edu_phone;
    }

    /**
     * 学历信息图片
     */
    public void setEdu_phone(String edu_phone) {
        this.edu_phone = edu_phone;
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
}