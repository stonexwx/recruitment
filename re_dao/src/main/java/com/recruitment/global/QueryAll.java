package com.recruitment.global;

import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.domain.ReInfo;
import com.recruitment.dao.dto.ReinfoDTO;

import java.util.List;

public class QueryAll {
    private String name;
    private String address;
    private String other;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
