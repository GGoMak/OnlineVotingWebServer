package com.ggomak.vote.springboot;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.repository.*;
import com.ggomak.vote.springboot.service.UserService;
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
    public CommandLineRunner runner(UserService userService, BoardRepository boardRepository, UserRepository userRepository, CandidateRepository candidateRepository, VoteRepository voteRepository){
        return args -> {

            User admin = userRepository.save(User.builder()
                    .name("관리자")
                    .studentId("20150000")
                    .department(Department.computerScience)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("4"))
                    .phoneNumber("010-1234-5678")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.ADMIN)
                    .build()
            );

            for(int i = 11; i < 30; i++){
                userRepository.save(User.builder()
                        .name("컴학" + Integer.toString(i-9))
                        .studentId("201500" + i)
                        .department(Department.computerScience)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("4"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("컴학" + Integer.toString(i-9))
                        .studentId("201600" + i)
                        .department(Department.computerScience)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("3"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("컴학" + Integer.toString(i-9))
                        .studentId("201700" + i)
                        .department(Department.computerScience)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("2"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("컴학" + Integer.toString(i-9))
                        .studentId("201800" + i)
                        .department(Department.computerScience)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("1"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
            }

            for(int i = 31; i < 60; i++){
                userRepository.save(User.builder()
                        .name("철학" + Integer.toString(i-29))
                        .studentId("201500" + i)
                        .department(Department.philosophy)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("4"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("철학" + Integer.toString(i-29))
                        .studentId("201600" + i)
                        .department(Department.philosophy)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("3"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("철학" + Integer.toString(i-29))
                        .studentId("201700" + i)
                        .department(Department.philosophy)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("2"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("철학" + Integer.toString(i-29))
                        .studentId("201800" + i)
                        .department(Department.philosophy)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("1"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
            }

            for(int i = 60; i < 100; i++){
                userRepository.save(User.builder()
                        .name("행정" + Integer.toString(i-59))
                        .studentId("201500" + i)
                        .department(Department.administration)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("4"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("행정" + Integer.toString(i-59))
                        .studentId("201600" + i)
                        .department(Department.administration)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("3"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("행정" + Integer.toString(i-59))
                        .studentId("201700" + i)
                        .department(Department.administration)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("2"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("행정" + Integer.toString(i-59))
                        .studentId("201800" + i)
                        .department(Department.administration)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("1"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
            }

            for(int i = 101; i < 120; i++){
                userRepository.save(User.builder()
                        .name("회계" + Integer.toString(i-99))
                        .studentId("20150" + i)
                        .department(Department.accounting)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("4"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("회계" + Integer.toString(i-99))
                        .studentId("20160" + i)
                        .department(Department.accounting)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("3"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("회계" + Integer.toString(i-99))
                        .studentId("20170" + i)
                        .department(Department.accounting)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("2"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("회계" + Integer.toString(i-99))
                        .studentId("20180" + i)
                        .department(Department.accounting)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("1"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
            }

            for(int i = 120; i < 150; i++){
                userRepository.save(User.builder()
                        .name("전자" + Integer.toString(i-119))
                        .studentId("20150" + i)
                        .department(Department.electronicEngineering)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("4"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("전자" + Integer.toString(i-119))
                        .studentId("20160" + i)
                        .department(Department.electronicEngineering)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("3"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("전자" + Integer.toString(i-119))
                        .studentId("20170" + i)
                        .department(Department.electronicEngineering)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("2"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("전자" + Integer.toString(i-119))
                        .studentId("20180" + i)
                        .department(Department.electronicEngineering)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("1"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
            }

            for(int i = 150; i < 180; i++){
                userRepository.save(User.builder()
                        .name("물리" + Integer.toString(i-149))
                        .studentId("20150" + i)
                        .department(Department.physics)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("4"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("물리" + Integer.toString(i-149))
                        .studentId("20160" + i)
                        .department(Department.physics)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("3"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("물리" + Integer.toString(i-149))
                        .studentId("20170" + i)
                        .department(Department.physics)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("2"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
                userRepository.save(User.builder()
                        .name("물리" + Integer.toString(i-149))
                        .studentId("20180" + i)
                        .department(Department.physics)
                        .dateOfBirth("960000")
                        .grade(Long.parseLong("1"))
                        .phoneNumber("123-123-123")
                        .password("{noop}123")
                        .lastLoginTime(LocalDateTime.now())
                        .roleType(RoleType.GUEST)
                        .isVoted(false)
                        .build());
            }

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

            User csCandidateUser1 = userRepository.save(User.builder()
                    .name("컴학" + Integer.toString(1))
                    .studentId("20150010")
                    .department(Department.computerScience)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("4"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User csCandidateUser2 = userRepository.save(User.builder()
                    .name("컴학" + Integer.toString(1))
                    .studentId("20160010")
                    .department(Department.computerScience)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("3"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User csCandidateUser3 = userRepository.save(User.builder()
                    .name("컴학" + Integer.toString(1))
                    .studentId("20170010")
                    .department(Department.computerScience)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("2"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User philCandidateUser1 = userRepository.save(User.builder()
                    .name("철학" + Integer.toString(1))
                    .studentId("20150030")
                    .department(Department.philosophy)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("4"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User philCandidateUser2 = userRepository.save(User.builder()
                    .name("철학" + Integer.toString(1))
                    .studentId("20160030")
                    .department(Department.philosophy)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("3"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User philCandidateUser3 = userRepository.save(User.builder()
                    .name("철학" + Integer.toString(1))
                    .studentId("20170030")
                    .department(Department.philosophy)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("2"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User accCandidateUser1 = userRepository.save(User.builder()
                    .name("회계" + Integer.toString(1))
                    .studentId("20150100")
                    .department(Department.accounting)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("4"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User accCandidateUser2 = userRepository.save(User.builder()
                    .name("회계" + Integer.toString(1))
                    .studentId("20160100")
                    .department(Department.accounting)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("3"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            User accCandidateUser3 = userRepository.save(User.builder()
                    .name("회계" + Integer.toString(1))
                    .studentId("20170100")
                    .department(Department.accounting)
                    .dateOfBirth("960000")
                    .grade(Long.parseLong("2"))
                    .phoneNumber("123-123-123")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .isVoted(false)
                    .build());

            candidateRepository.save(Candidate.builder()
                    .teamName("TESTTEAM1")
                    .department(Department.computerScience)
                    .user1(csCandidateUser1)
                    .user2(csCandidateUser2)
                    .user3(csCandidateUser3)
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
                    .user1(philCandidateUser1)
                    .user2(philCandidateUser2)
                    .user3(philCandidateUser3)
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
                    .user1(accCandidateUser1)
                    .user2(accCandidateUser2)
                    .user3(accCandidateUser3)
                    .imgPath("/Users/ggomak/Desktop/VoteImgDir/")
                    .thumbnail1("TESTTEAM3_20150031.jpg")
                    .thumbnail2("TESTTEAM3_20150032.jpg")
                    .thumbnail3("TESTTEAM3_20150033.jpg")
                    .pledgePoster("Pledge_TESTTEAM3.jpg")
                    .build()
            );

            for(int i = 10; i < 14; i++){
                userService.updateIsVoted("201800" + i);
                userService.updateIsVoted("201700" + i);
                userService.updateIsVoted("201600" + i);
                userService.updateIsVoted("201500" + i);
            }

            for(int i = 14; i < 16; i++){
                userService.updateIsVoted("201800" + i);
                userService.updateIsVoted("201700" + i);
                userService.updateIsVoted("201600" + i);
                userService.updateIsVoted("201500" + i);
            }

            for(int i = 30; i < 34; i++){
                userService.updateIsVoted("201800" + i);
                userService.updateIsVoted("201700" + i);
                userService.updateIsVoted("201600" + i);
                userService.updateIsVoted("201500" + i);
            }

            for(int i = 34; i < 36; i++){
                userService.updateIsVoted("201800" + i);
                userService.updateIsVoted("201700" + i);
                userService.updateIsVoted("201600" + i);
                userService.updateIsVoted("201500" + i);
            }

            for(int i = 100; i < 104; i++){
                userService.updateIsVoted("20180" + i);
                userService.updateIsVoted("20170" + i);
                userService.updateIsVoted("20160" + i);
                userService.updateIsVoted("20150" + i);
            }

            for(int i = 104; i < 106; i++){
                userService.updateIsVoted("20180" + i);
                userService.updateIsVoted("20170" + i);
                userService.updateIsVoted("20160" + i);
                userService.updateIsVoted("20150" + i);
            }

            IntStream.rangeClosed(1, 8).forEach(index ->
                    voteRepository.save(Vote.builder()
                    .candidate(candidateRepository.findById(Long.parseLong("1")).get())
                    .opposite(true)
                    .voteTime(LocalDateTime.of(2020, 7, 3, 9, 10, 10))
                    .build()
            ));

            IntStream.rangeClosed(1, 8).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("1")).get())
                            .opposite(true)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 11, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 4).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("1")).get())
                            .opposite(false)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 9, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 4).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("1")).get())
                            .opposite(false)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 12, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 7).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("2")).get())
                            .opposite(true)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 10, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 9).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("2")).get())
                            .opposite(true)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 14, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 8).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("2")).get())
                            .opposite(false)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 9, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 5).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("3")).get())
                            .opposite(true)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 13, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 11).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("3")).get())
                            .opposite(true)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 15, 10, 10))
                            .build()
                    ));

            IntStream.rangeClosed(1, 8).forEach(index ->
                    voteRepository.save(Vote.builder()
                            .candidate(candidateRepository.findById(Long.parseLong("3")).get())
                            .opposite(false)
                            .voteTime(LocalDateTime.of(2020, 7, 3, 16, 10, 10))
                            .build()
                    ));
        };
    }
}
