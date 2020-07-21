package com.ggomak.vote.springboot.domain;

import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class User implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String name;

    @Column
    private String studentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column
    private String dateOfBirth;

    @Column
    private Long grade;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    @Column
    private LocalDateTime lastLoginTime;

    @Column
    private String lastLoginIpAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Column
    private boolean isVoted;

    @Builder
    public User(String name, String studentId, Department department, String dateOfBirth, Long grade, String phoneNumber, String password, LocalDateTime lastLoginTime, String lastLoginIpAddress, RoleType roleType, boolean isVoted){
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIpAddress = lastLoginIpAddress;
        this.roleType = roleType;
        this.isVoted = isVoted;
    }

    public void updateRole(RoleType roleType){
        this.roleType = roleType;
    }

    public void updateisVoted(boolean value) {
        this.isVoted = value;
    }

    public String getRoleKey() {
        return this.roleType.getKey();
    }
}
