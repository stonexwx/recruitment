package com.recruitment.global;

import com.recruitment.dao.domain.Enterprise;
import com.recruitment.dao.domain.ReInfo;
import com.recruitment.dao.dto.ReinfoDTO;

import java.util.List;

public class QueryAll {
    private List<Enterprise> enterprise;
    private List<ReinfoDTO> reInfo;

    public List<Enterprise> getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(List<Enterprise> enterprise) {
        this.enterprise = enterprise;
    }

    public List<ReinfoDTO> getReInfo() {
        return reInfo;
    }

    public void setReInfo(List<ReinfoDTO> reInfo) {
        this.reInfo = reInfo;
    }
}
