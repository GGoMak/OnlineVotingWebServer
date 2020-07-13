package com.ggomak.vote.springboot.domain;

import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String phoneNumber;

    @Column
    private String password;

    @Column
    private LocalDateTime lastLoginTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Column
    private boolean isVoted;

    @Builder
    public User(String name, String studentId, Department department, String dateOfBirth, String phoneNumber, String password, LocalDateTime lastLoginTime, RoleType roleType, boolean isVoted){
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.roleType = roleType;
        this.isVoted = isVoted;
    }

    public void updateCandidate(){
        this.roleType = RoleType.CANDIDATE;
    }

    public void updateGuest(){
        this.roleType = RoleType.GUEST;
    }

    public void updateVoter(){
        this.roleType = RoleType.VOTER;
    }

    public void updateAdmin() {
        this.roleType = RoleType.ADMIN;
    }

    public void updateisVoted(boolean value) {
        this.isVoted = value;
    }

    public String getRoleKey() {
        return this.roleType.getKey();
    }
}
