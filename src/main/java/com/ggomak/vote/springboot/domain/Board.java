package com.ggomak.vote.springboot.domain;

import com.ggomak.vote.springboot.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Board implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Board(String title, String content, BoardType boardType, LocalDateTime createdDate, LocalDateTime updatedDate, User user){
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }

    public void update(String title, String content, BoardType boardType) {    // 게시글 업데이트 메소드
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.updatedDate = LocalDateTime.now();
    }

    public void setBoard(LocalDateTime createdDate, LocalDateTime updatedDate, User user){
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }
}
