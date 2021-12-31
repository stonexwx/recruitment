package com.recruitment.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息
 * @TableName enterprise
 */
@TableName(value ="enterprise")
public class Enterprise extends Object implements Serializable {
    /**
     * 企业id
     */
    @TableId(value = "eid", type = IdType.AUTO)
    private Long eid;

    /**
     * 
     */
    @TableField(value = "addtime")
    private Date addtime;

    /**
     * 企业编号
     */
    @TableField(value = "number")
    private String number;

    /**
     * 企业名称
     */
    @TableField(value = "ename")
    private String ename;

    /**
     * 负责人
     */
    @TableField(value = "principal")
    private String principal;

    /**
     * 联系电话
     */
    @TableField(value = "ephone")
    private String ephone;

    /**
     * 企业邮箱
     */
    @TableField(value = "e_email")
    private String e_email;

    /**
     * 企业介绍
     */
    @TableField(value = "e_introduce")
    private String e_introduce;

    /**
     * 媒体
     */
    @TableField(value = "media")
    private String media;

    /**
     * 评价得分
     */
    @TableField(value = "evaluation")
    private String evaluation;

    /**
     * 企业规模
     */
    @TableField(value = "scale")
    private String scale;

    /**
     * 企业地点
     */
    @TableField(value = "address")
    private String address;

    private String photo;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
     * 企业编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 企业编号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 企业名称
     */
    public String getEname() {
        return ename;
    }

    /**
     * 企业名称
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * 负责人
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * 负责人
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * 联系电话
     */
    public String getEphone() {
        return ephone;
    }

    /**
     * 联系电话
     */
    public void setEphone(String ephone) {
        this.ephone = ephone;
    }

    /**
     * 企业邮箱
     */
    public String getE_email() {
        return e_email;
    }

    /**
     * 企业邮箱
     */
    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    /**
     * 企业介绍
     */
    public String getE_introduce() {
        return e_introduce;
    }

    /**
     * 企业介绍
     */
    public void setE_introduce(String e_introduce) {
        this.e_introduce = e_introduce;
    }

    /**
     * 媒体
     */
    public String getMedia() {
        return media;
    }

    /**
     * 媒体
     */
    public void setMedia(String media) {
        this.media = media;
    }

    /**
     * 评价得分
     */
    public String getEvaluation() {
        return evaluation;
    }

    /**
     * 评价得分
     */
    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * 企业规模
     */
    public String getScale() {
        return scale;
    }

    /**
     * 企业规模
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * 企业地点
     */
    public String getAddress() {
        return address;
    }

    /**
     * 企业地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "eid=" + eid +
                ", addtime=" + addtime +
                ", number='" + number + '\'' +
                ", ename='" + ename + '\'' +
                ", principal='" + principal + '\'' +
                ", ephone='" + ephone + '\'' +
                ", e_email='" + e_email + '\'' +
                ", e_introduce='" + e_introduce + '\'' +
                ", media='" + media + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", scale='" + scale + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}