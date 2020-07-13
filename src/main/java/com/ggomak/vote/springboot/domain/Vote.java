package com.ggomak.vote.springboot.domain;

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
public class Vote implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @OneToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

    @Column
    private LocalDateTime voteTime;

    @Column
    private boolean opposite;

    @Column
    private String ipAddress;

    @Builder
    public Vote(Candidate candidate, LocalDateTime voteTime, boolean opposite, String ipAddress){
        this.candidate = candidate;
        this.voteTime = voteTime;
        this.opposite = opposite;
        this.ipAddress = ipAddress;
    }
}
