package com.recruitment.dao.dto;

import com.recruitment.dao.domain.ReInfo;

public class ReinfoDTO {
    private ReInfo reInfo =new ReInfo();
    private String job2Name;

    public ReInfo getReInfo() {
        return reInfo;
    }

    public void setReInfo(ReInfo reInfo) {
        this.reInfo = reInfo;
    }

    public String getJob2Name() {
        return job2Name;
    }

    public void setJob2Name(String job2Name) {
        this.job2Name = job2Name;
    }
}
