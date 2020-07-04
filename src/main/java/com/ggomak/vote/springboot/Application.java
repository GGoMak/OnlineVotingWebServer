package com.ggomak.vote.springboot;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.repository.BoardRepository;
import com.ggomak.vote.springboot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(BoardRepository boardRepository, UserRepository userRepository){
        return args -> {

            User admin = userRepository.save(User.builder()
                    .name("관리자")
                    .studentId("20150000")
                    .department(Department.computerScience)
                    .dateOfBirth("960000")
                    .phoneNumber("010-1234-5678")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.ADMIN)
                    .build()
            );

            User user1 = userRepository.save(User.builder()
                    .name("테스트")
                    .studentId("20150001")
                    .department(Department.accounting)
                    .dateOfBirth("960000")
                    .phoneNumber("010-431-8765")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.VOTER)
                    .build()
            );

            IntStream.rangeClosed(1, 20).forEach(index ->
                    boardRepository.save(Board.builder()
                            .title("게시글" + index)
                            .content("Content")
                            .boardType(BoardType.notice)
                            .createdDate(LocalDateTime.now())
                            .updatedDate(LocalDateTime.now())
                            .user(admin)
                            .build()));

            IntStream.rangeClosed(1, 20).forEach(index ->
                    boardRepository.save(Board.builder()
                            .title("게시글" + index)
                            .content("Content")
                            .boardType(BoardType.free)
                            .createdDate(LocalDateTime.now())
                            .updatedDate(LocalDateTime.now())
                            .user(admin)
                            .build()));

            boardRepository.save(Board.builder()
                    .title("OO 대학교 온라인 투표 시스템 이용 방법 안내")
                    .content("OO 대학교 온라인 투표 시스템 이용 방법 안내")
                    .boardType(BoardType.notice)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .user(admin)
                    .build());

            boardRepository.save(Board.builder()
                    .title("OOO 투표합시다!")
                    .content("OOO 투표합시다!")
                    .boardType(BoardType.free)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .user(admin)
                    .build());

            boardRepository.save(Board.builder()
                    .title("!!삭제해주세요!!")
                    .content("!!삭제해주세요!!")
                    .boardType(BoardType.free)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .user(admin)
                    .build());
        };
    }
}
