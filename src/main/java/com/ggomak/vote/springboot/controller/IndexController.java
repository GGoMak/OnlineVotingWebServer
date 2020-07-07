package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.Pledge;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.oauthsecurity.annotation.LoginUser;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.service.BoardService;
import com.ggomak.vote.springboot.service.UserService;
import com.ggomak.vote.springboot.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Session;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;
    private final UserService userService;
    private final VoteService voteService;

    @GetMapping("/")
    public String main(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "main";
    }

    @GetMapping("/notice")
    public String notice(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.notice));
        model.addAttribute("sessionUser", user);
        return "notice";
    }

    @GetMapping("/free")
    public String free(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.free));
        model.addAttribute("sessionUser", user);
        return "free";
    }

    @GetMapping("/board")
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        model.addAttribute("sessionUser", user);
        return "boardform";
    }

    @GetMapping("/post")
    public String post(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "postform";
    }

    @GetMapping("/modify")
    public String postModify(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        model.addAttribute("sessionUser", user);
        return "postform";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/userinfo")
    public String userInfo(@RequestParam(value = "type", defaultValue = "all") String type, @RequestParam(value = "value", required = false) String value, @PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("userList", userService.findUserList(pageable, type, value));
        model.addAttribute("sessionUser", user);
        return "userinfo";
    }

    @GetMapping("/voteinfo")
    public String voteInfo(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "voteinfo";
    }

    @GetMapping("/votelist")
    public String voteList(Model model, @LoginUser SessionUser user){
        model.addAttribute("voteList", userService.findCandidateList());
        model.addAttribute("sessionUser", user);
        return "votelist";
    }

    @GetMapping("/pledges")
    public String pledges(@RequestParam(value = "department") String department, Model model, @LoginUser SessionUser user){
        model.addAttribute("pledge", voteService.findPledge(department));
        model.addAttribute("sessionUser", user);
        return "pledges";
    }
}
