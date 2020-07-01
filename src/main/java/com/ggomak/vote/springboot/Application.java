package com.ggomak.vote.springboot;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.repository.BoardRepository;
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
    public CommandLineRunner runner(BoardRepository boardRepository){
        return args -> {

            boardRepository.save(Board.builder()
                    .title("OO 대학교 온라인 투표 시스템 이용 방법 안내")
                    .content("OO 대학교 온라인 투표 시스템 이용 방법 안내")
                    .boardType(BoardType.notice)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build());

            boardRepository.save(Board.builder()
                    .title("OOO 투표합시다!")
                    .content("OOO 투표합시다!")
                    .boardType(BoardType.free)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build());
        };
    }
}
