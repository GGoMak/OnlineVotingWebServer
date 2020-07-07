package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.repository.UserRepository;
import com.ggomak.vote.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VoteApiController {

    private final UserService userService;

    @PostMapping("/api/v3/candidate/reg/{idx}")
    public Long candidateRegistration(@PathVariable Long idx){
        return userService.candidateRegistration(idx);
    }
}
