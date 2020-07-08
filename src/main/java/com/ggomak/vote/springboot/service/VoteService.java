package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.Pledge;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.dto.VoteDTO;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.repository.CandidateRepository;
import com.ggomak.vote.springboot.repository.PledgeRepository;
import com.ggomak.vote.springboot.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final PledgeRepository pledgeRepository;
    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;

    public Pledge findPledge(String department){

        switch (department) {
            case "컴퓨터학부":
                return pledgeRepository.findByDepartment(Department.computerScience);
            case "철학과":
                return pledgeRepository.findByDepartment(Department.philosophy);
            case "회계학과":
                return pledgeRepository.findByDepartment(Department.accounting);
            case "전자공학과":
                return pledgeRepository.findByDepartment(Department.electronicEngineering);
            case "물리학과":
                return pledgeRepository.findByDepartment(Department.physics);
            case "행정학과":
                return pledgeRepository.findByDepartment(Department.administration);
            default:
                return null;
        }
    }

    public Candidate findCandidate(Long idx){

        return candidateRepository.findById(idx).get();
    }

    @Transactional
    public Long vote(VoteDTO voteDTO){

        Vote vote = Vote.builder()
                .candidate(candidateRepository.findById(voteDTO.getIdx()).get())
                .voteTime(LocalDateTime.now())
                .opposite(voteDTO.isOpposite())
                .build();

        return voteRepository.save(vote).getIdx();
    }
}
