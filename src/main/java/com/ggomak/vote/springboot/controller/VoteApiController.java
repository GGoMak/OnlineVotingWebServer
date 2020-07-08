package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.dto.VoteDTO;
import com.ggomak.vote.springboot.service.UserService;
import com.ggomak.vote.springboot.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VoteApiController {

    private final UserService userService;
    private final VoteService voteService;

    @PostMapping("/api/v3/candidate/reg/{idx}")
    public Long candidateRegistration(@PathVariable Long idx){
        return userService.candidateRegistration(idx);
    }

    @PostMapping("/api/v3/vote")
    public Long vote(@RequestBody VoteDTO vote){
        return voteService.vote(vote);
    }
}
