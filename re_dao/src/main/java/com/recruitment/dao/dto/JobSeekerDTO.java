package com.recruitment.dao.dto;

import com.recruitment.dao.domain.JobSeeker;

public class JobSeekerDTO {
    private JobSeeker jobSeeker =new JobSeeker();
    private String jobName;

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
}
