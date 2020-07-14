package com.ggomak.vote.springboot;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.repository.*;
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
    public CommandLineRunner runner(BoardRepository boardRepository, UserRepository userRepository, CandidateRepository candidateRepository, VoteRepository voteRepository){
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
                            .name("컴학" + index)
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
                            .name("철학" + index)
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
                            .name("회계" + index)
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
                    .imgPath("/Users/ggomak/Desktop/VoteImgDir/")
                    .thumbnail1("TESTTEAM1_20150011.jpg")
                    .thumbnail2("TESTTEAM1_20150012.jpg")
                    .thumbnail3("TESTTEAM1_20150013.jpg")
                    .pledgePoster("Pledge_TESTTEAM1.jpg")
                    .build()
            );

            candidateRepository.save(Candidate.builder()
                    .teamName("TESTTEAM2")
                    .department(Department.philosophy)
                    .user1(userRepository.findById(Long.valueOf(11)).get())
                    .user2(userRepository.findById(Long.valueOf(12)).get())
                    .user3(userRepository.findById(Long.valueOf(13)).get())
                    .imgPath("/Users/ggomak/Desktop/VoteImgDir/")
                    .thumbnail1("TESTTEAM2_20150021.jpg")
                    .thumbnail2("TESTTEAM2_20150022.jpg")
                    .thumbnail3("TESTTEAM2_20150023.jpg")
                    .pledgePoster("Pledge_TESTTEAM2.jpg")
                    .build()
            );

            candidateRepository.save(Candidate.builder()
                    .teamName("TESTTEAM3")
                    .department(Department.accounting)
                    .user1(userRepository.findById(Long.valueOf(20)).get())
                    .user2(userRepository.findById(Long.valueOf(21)).get())
                    .user3(userRepository.findById(Long.valueOf(22)).get())
                    .imgPath("/Users/ggomak/Desktop/VoteImgDir/")
                    .thumbnail1("TESTTEAM1_20150031.jpg")
                    .thumbnail2("TESTTEAM1_20150032.jpg")
                    .thumbnail3("TESTTEAM1_20150033.jpg")
                    .pledgePoster("Pledge_TESTTEAM3.jpg")
                    .build()
            );
        };
    }
}
