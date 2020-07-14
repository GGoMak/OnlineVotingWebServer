package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    ArrayList<Candidate> findByDepartment(Department department);
}
