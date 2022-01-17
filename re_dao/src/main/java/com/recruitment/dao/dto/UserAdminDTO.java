package com.recruitment.dao.dto;

import java.util.Date;

public class UserAdminDTO {
    private String userName;
    private String userRealName;
    private String userMobile;
    private String userSex;
    private String userEmail;
    private String userPassword;
    private Date editTime;
    private Long uid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        switch (userSex) {
            case "0":
                this.userSex = "男";
                break;
            case "1":
                this.userSex = "女";
                break;
            case "男":
                this.userSex = "0";
                break;
            default:
                this.userSex = "1";
                break;
        }
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
