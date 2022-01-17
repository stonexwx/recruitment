package com.recruitment.dao.dto;

import java.util.Date;

public class EnterpriseAdminDTO {
    private Long eid;
    private String deptName;
    private String deptNo;
    private String deptAdd;
    private String deptType;
    private Date editTime;

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptAdd() {
        return deptAdd;
    }

    public void setDeptAdd(String deptAdd) {
        this.deptAdd = deptAdd;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
