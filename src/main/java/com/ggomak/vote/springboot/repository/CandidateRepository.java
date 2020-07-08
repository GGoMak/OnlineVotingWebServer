package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByDepartment(Department department);
}
