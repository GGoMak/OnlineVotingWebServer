package com.ggomak.vote.springboot;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.Pledge;
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

            Pledge pledge = pledgeRepository.save(Pledge.builder()
                    .department(Department.computerScience)
                    .posterFileName("pic02.jpg")
                    .title("XXX OOO 선거운동 본부")
                    .posterPath("/images/")
                    .build()
            );

            IntStream.rangeClosed(10, 15).forEach(index ->
                    userRepository.save(User.builder()
                            .name("학생"+index)
                            .studentId("201500"+index)
                            .department(Department.computerScience)
                            .dateOfBirth("960000")
                            .phoneNumber("010-7777-7777")
                            .password("{noop}123")
                            .lastLoginTime(LocalDateTime.now())
                            .roleType(RoleType.GUEST)
                            .build()));

            userRepository.save(User.builder()
                    .name("손채영")
                    .studentId("20150002")
                    .department(Department.administration)
                    .dateOfBirth("990423")
                    .phoneNumber("010-1111-1111")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.GUEST)
                    .build()
            );
            userRepository.save(User.builder()
                    .name("유정현")
                    .studentId("20150003")
                    .department(Department.electronicEngineering)
                    .dateOfBirth("991101")
                    .phoneNumber("010-2222-2222")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.GUEST)
                    .build()
            );
            User user2 = userRepository.save(User.builder()
                    .name("임나연")
                    .studentId("20150004")
                    .department(Department.computerScience)
                    .dateOfBirth("950922")
                    .phoneNumber("010-3333-3333")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
                    .build()
            );
            userRepository.save(User.builder()
                    .name("김다현")
                    .studentId("20150005")
                    .department(Department.administration)
                    .dateOfBirth("980528")
                    .phoneNumber("010-4444-4444")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.GUEST)
                    .build()
            );
            userRepository.save(User.builder()
                    .name("묘이 미나")
                    .studentId("20150006")
                    .department(Department.physics)
                    .dateOfBirth("970323")
                    .phoneNumber("010-5555-5555")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.GUEST)
                    .build()
            );
            User user3 = userRepository.save(User.builder()
                    .name("미나토자키 사나")
                    .studentId("20150007")
                    .department(Department.computerScience)
                    .dateOfBirth("961229")
                    .phoneNumber("010-6666-6666")
                    .password("{noop}123")
                    .lastLoginTime(LocalDateTime.now())
                    .roleType(RoleType.CANDIDATE)
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

            Candidate candidate = candidateRepository.save(Candidate.builder()
                    .department(Department.computerScience)
                    .imgPath("/images/")
                    .teamName("선거운동 본부")
                    .user1(user2)
                    .user2(user3)
                    .thumbnail1("pic03.jpg")
                    .thumbnail2("pic04.jpg")
                    .thumbnail3(null)
                    .build()
            );
        };
    }
}
