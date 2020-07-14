package com.ggomak.vote.springboot.domain;

import com.ggomak.vote.springboot.domain.enums.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Candidate implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String teamName;

    @Column
    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    private User candidate1;

    @OneToOne(fetch = FetchType.LAZY)
    private User candidate2;

    @OneToOne(fetch = FetchType.LAZY)
    private User candidate3;

    @Column
    private String imgPath;

    @Column
    private String pledgePoster;

    @Column
    private String thumbnail1;

    @Column
    private String thumbnail2;

    @Column
    private String thumbnail3;

    @Builder
    public Candidate(String teamName, Department department, User user1, User user2, User user3, String imgPath, String pledgePoster, String thumbnail1, String thumbnail2, String thumbnail3){
        this.teamName = teamName;
        this.department = department;
        this.candidate1 = user1;
        this.candidate2 = user2;
        this.candidate3 = user3;
        this.imgPath = imgPath;
        this.pledgePoster = pledgePoster;
        this.thumbnail1 = thumbnail1;
        this.thumbnail2 = thumbnail2;
        this.thumbnail3 = thumbnail3;
    }
}
