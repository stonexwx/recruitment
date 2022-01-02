package com.recruitment.dao.dto;

import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;

public class UserDTO {
    private Users users  = new Users();
    private JobSeeker jobSeeker = new JobSeeker();

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }
}
