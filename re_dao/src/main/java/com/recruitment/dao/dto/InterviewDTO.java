package com.recruitment.dao.dto;

import java.util.Date;

public class InterviewDTO {
    private Long iid;
    private Date forWardTime;
    private String forWardDept;
    private String userName;
    private String  userMobile;
    public Date getForWardTime() {
        return forWardTime;
    }

    public void setForWardTime(Date forWardTime) {
        this.forWardTime = forWardTime;
    }

    public String getForWardDept() {
        return forWardDept;
    }

    public void setForWardDept(String forWardDept) {
        this.forWardDept = forWardDept;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

}
