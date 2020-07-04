package com.ggomak.vote.springboot.oauthsecurity.auth.dto;

import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String studentId;
    private Department department;
    private RoleType roleType;

    public SessionUser(User user) {
        this.name = user.getName();
        this.studentId = user.getStudentId();
        this.department = user.getDepartment();
        this.roleType = user.getRoleType();
    }
}