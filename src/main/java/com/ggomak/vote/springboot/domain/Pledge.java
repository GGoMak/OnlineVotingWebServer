package com.ggomak.vote.springboot.domain;

import com.ggomak.vote.springboot.domain.enums.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Pledge implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column
    private String title;

    @Column
    private String posterPath;

    @Column
    private String posterFileName;

    @Builder
    public Pledge(Department department, String title, String posterPath, String posterFileName){
        this.department = department;
        this.title = title;
        this.posterPath = posterPath;
        this.posterFileName = posterFileName;
    }
}
