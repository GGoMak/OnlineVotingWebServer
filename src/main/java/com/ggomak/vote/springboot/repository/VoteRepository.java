package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByVoteTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Vote> findAll();
}
