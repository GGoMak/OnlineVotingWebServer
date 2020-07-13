package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.Pledge;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.dto.VoteDTO;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.repository.CandidateRepository;
import com.ggomak.vote.springboot.repository.PledgeRepository;
import com.ggomak.vote.springboot.repository.UserRepository;
import com.ggomak.vote.springboot.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final PledgeRepository pledgeRepository;
    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

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

    public Candidate findCandidateByIdx(Long idx) {

        return candidateRepository.findById(idx).get();
    }

    public List<Candidate> findCandidateByDepartment(Department department, SessionUser sessionUser) {

        User user = userRepository.findByStudentId(sessionUser.getStudentId()).get();

        if(user.isVoted()){
            return null;
        }

        List<Candidate> candidates = new ArrayList<>();

        candidates.add(candidateRepository.findByDepartment(department));

        return candidates;
    }

    @Transactional
    public Long vote(VoteDTO voteDTO, SessionUser sessionUser, HttpServletRequest request){

        User user = userRepository.findByStudentId(sessionUser.getStudentId()).get();
        user.updateisVoted(true);

        Vote vote = Vote.builder()
                .candidate(candidateRepository.findById(voteDTO.getIdx()).get())
                .voteTime(LocalDateTime.now())
                .opposite(voteDTO.isOpposite())
                .ipAddress(getRemoteAddr(request))
                .build();

        return voteRepository.save(vote).getIdx();
    }

    protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }
}
