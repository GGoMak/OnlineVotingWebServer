package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.dto.CandidateRegDto;
import com.ggomak.vote.springboot.domain.dto.UserRoleChangeDTO;
import com.ggomak.vote.springboot.domain.dto.VoteDTO;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.oauthsecurity.annotation.LoginUser;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.service.UserService;
import com.ggomak.vote.springboot.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class VoteApiController {

    private final UserService userService;
    private final VoteService voteService;

    @PostMapping("/api/v3/role")
    public Long roleChange(@RequestBody UserRoleChangeDTO userRoleChangeDTO){

        if(userRoleChangeDTO.getValue().equals("VOTER")){
            return userService.roleChangeAllVoter(userRoleChangeDTO);
        }
        else {
            return userService.roleChange(userRoleChangeDTO);
        }
    }

    @PostMapping("/api/v3/vote")
    public Long vote(@RequestBody VoteDTO vote, @LoginUser SessionUser user, HttpServletRequest request){
        return voteService.vote(vote, user, request);
    }

    @PostMapping("/api/v3/getuser/{studentId}")
    public User getUser(@PathVariable String studentId) {
        return userService.getUser(studentId);
    }

    @PostMapping("/api/v3/reguser")
    public Long regUser(HttpServletRequest request, @RequestParam(value = "poster") MultipartFile pledge, @RequestParam(value = "candidate1_pic") MultipartFile picture1, @RequestParam(value = "candidate2_pic") MultipartFile picture2, @RequestParam(value = "candidate3_pic", required = false) MultipartFile picture3){

        CandidateRegDto requestDto = new CandidateRegDto(
                Department.valueOf(request.getParameter("department")),
                request.getParameter("teamName"),
                request.getParameter("candidate1"),
                request.getParameter("candidate2"),
                request.getParameter("candidate3")
        );

        ArrayList<MultipartFile> pictureDto = new ArrayList<>();
        pictureDto.add(picture1);
        pictureDto.add(picture2);
        if(picture3 != null){
            pictureDto.add(picture3);
        }

        return userService.registCandidate(requestDto, pledge, pictureDto);
    }
}
