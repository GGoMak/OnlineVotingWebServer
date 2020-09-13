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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        LocalDateTime now = LocalDateTime.of(2020, 9, 13, 18, 0, 0);
        LocalDateTime time = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 9, 00, 00);

        for (LocalDateTime i = time.plusHours(1); i.isBefore(now); i = i.plusHours(1)) {
            try {
                long voteCount = voteRepository.countByBetweenVoteTime(time, i);
                long allCount = userRepository.countAll();
                result.add((int)((double)voteCount/(double)allCount * 100));
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
            long allCount = userRepository.countByGrade(Long.valueOf(i));
            result.add((long)((double)count/(double)allCount * 100));
        }
        return result;
    }

    public Map<Department, Long> getDepartmentVoteResult() {
        long count, allCount;
        Map<Department, Long> data = new HashMap<>();

        /* 효율적인 코드 수정 필요 */
        count = voteRepository.countByDepartment(Department.computerScience);
        allCount = userRepository.countByDepartment(Department.computerScience);
        data.put(Department.computerScience, (long)((double)count/(double)allCount * 100));
        count = voteRepository.countByDepartment(Department.accounting);
        allCount = userRepository.countByDepartment(Department.accounting);
        data.put(Department.accounting, (long)((double)count/(double)allCount * 100));
        count = voteRepository.countByDepartment(Department.administration);
        allCount = userRepository.countByDepartment(Department.administration);
        data.put(Department.administration, (long)((double)count/(double)allCount * 100));
        count = voteRepository.countByDepartment(Department.philosophy);
        allCount = userRepository.countByDepartment(Department.philosophy);
        data.put(Department.philosophy, (long)((double)count/(double)allCount * 100));
        count = voteRepository.countByDepartment(Department.electronicEngineering);
        allCount = userRepository.countByDepartment(Department.electronicEngineering);
        data.put(Department.electronicEngineering, (long)((double)count/(double)allCount * 100));
        count = voteRepository.countByDepartment(Department.physics);
        allCount = userRepository.countByDepartment(Department.physics);
        data.put(Department.physics, (long)((double)count/(double)allCount * 100));

        return data;
    }

    public ArrayList<Integer> getVoteResult(Department department) {

        ArrayList<Integer> result = new ArrayList<>();

        long allCount = userRepository.countByDepartment(department);
        long agreeCount = voteRepository.countByVotedDepartment(department);
        long disagreeCount = voteRepository.countByNotVotedDepartment(department);

        result.add((int)agreeCount);
        result.add((int)disagreeCount);
        result.add((int)(allCount - agreeCount - disagreeCount));

        return result;

    }
}
