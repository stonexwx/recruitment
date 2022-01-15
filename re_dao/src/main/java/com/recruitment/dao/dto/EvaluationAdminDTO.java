package com.recruitment.dao.dto;

public class EvaluationAdminDTO {
    private Long id;
    private String userName;
    private String userMobile;
    private String commentMessage;
    private String commentDept;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public String getCommentDept() {
        return commentDept;
    }

    public void setCommentDept(String commentDept) {
        this.commentDept = commentDept;
    }
}
