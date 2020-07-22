package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByVoteTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Vote> findAll();

    @Query("select count(v) from Vote v where v.voteTime between :start and :end")
    long countByBetweenVoteTime(LocalDateTime start, LocalDateTime end);

    @Query("select count(v) from Vote v where v.candidate.department = :department")
    long countByDepartment(Department department);

    @Query("select count(v) from Vote v where v.candidate.department = :department and v.opposite = true")
    long countByVotedDepartment(Department department);

    @Query("select count(v) from Vote v where v.candidate.department = :department and v.opposite = false")
    long countByNotVotedDepartment(Department department);
}
