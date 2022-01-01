package com.recruitment.dao.dto;

import com.recruitment.dao.domain.Evaluation;

public class EvaluationDTO {
    private Evaluation evaluation;
    private String userName;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
