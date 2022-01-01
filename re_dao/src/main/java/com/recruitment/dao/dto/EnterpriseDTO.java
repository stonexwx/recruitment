package com.recruitment.dao.dto;

import com.recruitment.dao.domain.Enterprise;

public class EnterpriseDTO {
    private Enterprise enterprise;
    private String number;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
