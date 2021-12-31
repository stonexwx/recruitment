package com.recruitment.dao.dto;

import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class JobSeekerDTO {
    private JobSeeker jobSeeker =new JobSeeker();
    private String jobName;
    private Users users;
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
