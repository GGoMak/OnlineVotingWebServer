package com.ggomak.vote.springboot;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.repository.BoardRepository;
import com.ggomak.vote.springboot.repository.CandidateRepository;
import com.ggomak.vote.springboot.repository.PledgeRepository;
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
    public CommandLineRunner runner(BoardRepository boardRepository, UserRepository userRepository, PledgeRepository pledgeRepository, CandidateRepository candidateRepository){
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

            IntStream.rangeClosed(1, 9).forEach(index ->
                    userRepository.save(User.builder()
                            .name("컴퓨터학부" + index)
                            .studentId("2015001" + index)
                            .department(Department.computerScience)
                            .dateOfBirth("960000")
                            .phoneNumber("123-123-123")
                            .password("{noop}123")
                            .lastLoginTime(LocalDateTime.now())
                            .roleType(RoleType.GUEST)
                            .isVoted(false)
                            .build()));

            IntStream.rangeClosed(1, 9).forEach(index ->
                    userRepository.save(User.builder()
                            .name("철학과" + index)
                            .studentId("2015002" + index)
                            .department(Department.philosophy)
                            .dateOfBirth("960000")
                            .phoneNumber("123-123-123")
                            .password("{noop}123")
                            .lastLoginTime(LocalDateTime.now())
                            .roleType(RoleType.GUEST)
                            .isVoted(false)
                            .build()));

            IntStream.rangeClosed(1, 9).forEach(index ->
                    userRepository.save(User.builder()
                            .name("회계학과" + index)
                            .studentId("2015003" + index)
                            .department(Department.accounting)
                            .dateOfBirth("960000")
                            .phoneNumber("123-123-123")
                            .password("{noop}123")
                            .lastLoginTime(LocalDateTime.now())
                            .roleType(RoleType.GUEST)
                            .isVoted(false)
                            .build()));

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

            candidateRepository.save(Candidate.builder()
                    .teamName("TESTTEAM1")
                    .department(Department.computerScience)
                    .user1(userRepository.findById(Long.valueOf(2)).get())
                    .user2(userRepository.findById(Long.valueOf(3)).get())
                    .user3(userRepository.findById(Long.valueOf(4)).get())
                    .imgPath("/images/")
                    .thumbnail1("pic01.jpg")
                    .thumbnail2("pic02.jpg")
                    .thumbnail3("pic03.jpg")
                    .build()
            );

            candidateRepository.save(Candidate.builder()
                    .teamName("TESTTEAM2")
                    .department(Department.philosophy)
                    .user1(userRepository.findById(Long.valueOf(11)).get())
                    .user2(userRepository.findById(Long.valueOf(12)).get())
                    .user3(userRepository.findById(Long.valueOf(13)).get())
                    .imgPath("/images/")
                    .thumbnail1("pic01.jpg")
                    .thumbnail2("pic02.jpg")
                    .thumbnail3("pic03.jpg")
                    .build()
            );

            candidateRepository.save(Candidate.builder()
                    .teamName("TESTTEAM3")
                    .department(Department.accounting)
                    .user1(userRepository.findById(Long.valueOf(20)).get())
                    .user2(userRepository.findById(Long.valueOf(21)).get())
                    .user3(userRepository.findById(Long.valueOf(22)).get())
                    .imgPath("/images/")
                    .thumbnail1("pic01.jpg")
                    .thumbnail2("pic02.jpg")
                    .thumbnail3("pic03.jpg")
                    .build()
            );
        };
    }
}
