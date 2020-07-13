package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.dto.UserRoleChangeDTO;
import com.ggomak.vote.springboot.domain.dto.VoteDTO;
import com.ggomak.vote.springboot.oauthsecurity.annotation.LoginUser;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.service.UserService;
import com.ggomak.vote.springboot.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class VoteApiController {

    private final UserService userService;
    private final VoteService voteService;

    @PostMapping("/api/v3/role")
    public Long roleChange(@RequestBody UserRoleChangeDTO userRoleChangeDTO){
        return userService.roleChange(userRoleChangeDTO);
    }

    @PostMapping("/api/v3/vote")
    public Long vote(@RequestBody VoteDTO vote, @LoginUser SessionUser user, HttpServletRequest request){
        return voteService.vote(vote, user, request);
    }
}
