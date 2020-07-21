package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.dto.VoteDTO;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.repository.CandidateRepository;
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

    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public Candidate findPledge(Long idx){

        return candidateRepository.findById(idx).get();
    }

    public Candidate findCandidateByIdx(Long idx) {

        return candidateRepository.findById(idx).get();
    }

    public List<Candidate> findCandidateByDepartment(Department department, SessionUser sessionUser) {

        User user = userRepository.findByStudentId(sessionUser.getStudentId()).get();

        if(user.isVoted()){
            return null;
        }

        List<Candidate> candidates = new ArrayList<>(candidateRepository.findByDepartment(department));

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

    public ArrayList<Integer> getRealTimeVoteResult() {

        ArrayList<Integer> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.of(2020, 7, 3, 18, 0, 0);
        LocalDateTime time = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 9, 00, 00);

        for (LocalDateTime i = time; i.isBefore(now); i = i.plusHours(1)) {
            try {
                List<Vote> vote = voteRepository.findAllByVoteTimeBetween(i, i.plusHours(1));
                result.add(vote.size());
            } catch (Exception e){
                continue;
            }
        }

        return result;
    }

    public ArrayList<Long> getGradeVoteResult() {

        ArrayList<Long> result = new ArrayList<>();

        for(int i = 1; i <= 4; i++){
            long count = userRepository.countByVoted(Long.valueOf(i));
            result.add(count);
        }
        return result;
    }

    public ArrayList<Integer> getDepartmentVoteResult(Department department) {

        ArrayList<Integer> result = new ArrayList<>();

        List<Vote> vote = voteRepository.findAll();
        int voteCount = 0;
        int departmentCount = 0;

        for(int i = 0; i < vote.size(); i++){
            if(vote.get(i).getCandidate().getDepartment() == department){
                if(vote.get(i).isOpposite()){
                    voteCount++;
                }
                departmentCount++;
            }
        }

        result.add(voteCount);
        result.add(departmentCount - voteCount);

        return result;

    }
}
