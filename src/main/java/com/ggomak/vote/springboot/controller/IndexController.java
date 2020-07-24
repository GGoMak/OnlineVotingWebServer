package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.oauthsecurity.annotation.LoginUser;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.service.BoardService;
import com.ggomak.vote.springboot.service.UserService;
import com.ggomak.vote.springboot.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;
    private final UserService userService;
    private final VoteService voteService;

    // 메인페이지
    @GetMapping("/")
    public String main(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "main";
    }

    // 공지사항
    @GetMapping("/notice")
    public String notice(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.notice));
        model.addAttribute("sessionUser", user);
        return "notice";
    }

    // 자유게시판
    @GetMapping("/free")
    public String free(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.free));
        model.addAttribute("sessionUser", user);
        return "free";
    }

    // 게시글 읽기
    @GetMapping("/board")
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        model.addAttribute("sessionUser", user);
        return "boardform";
    }

    // 게시글 쓰기
    @GetMapping("/post")
    public String post(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "postform";
    }

    // 게시글 수정
    @GetMapping("/modify")
    public String postModify(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        model.addAttribute("sessionUser", user);
        return "postform";
    }

    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    // 유저 리스트(후보자 등록)
    @GetMapping("/userinfo")
    public String userInfo(@RequestParam(value = "type", defaultValue = "all") String type, @RequestParam(value = "value", required = false) String value, @PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("userList", userService.findUserList(pageable, type, value));
        model.addAttribute("sessionUser", user);
        return "userinfo";
    }

    // 투표 안내
    @GetMapping("/voteinfo")
    public String voteInfo(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "voteinfo";
    }

    // 후보자 리스트(공약보기)
    @GetMapping("/pledgelist")
    public String voteList(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("pledgeList", userService.findCandidateList(pageable));
        model.addAttribute("sessionUser", user);
        return "pledgelist";
    }

    // 공약
    @GetMapping("/pledges")
    public String pledges(@RequestParam(value = "idx") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("pledge", voteService.findPledge(idx));
        model.addAttribute("sessionUser", user);
        return "pledges";
    }

    // 전자서명
    @GetMapping("/signature")
    public String signature(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "signature";
    }

    // 투표 리스트
    @GetMapping("/votelist")
    public String votelist(Model model, @LoginUser SessionUser user){
        model.addAttribute("voteList", voteService.findCandidateByDepartment(user.getDepartment(), user));
        model.addAttribute("sessionUser", user);
        return "votelist";
    }

    // 투표하기
    @GetMapping("/vote")
    public String vote(@RequestParam(value = "idx") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("candidate", voteService.findCandidateByIdx(idx));
        model.addAttribute("sessionUser", user);
        return "vote";
    }

    // 투표종료
    @GetMapping("/voteend")
    public String voteEnd(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "voteend";
    }

    // 투표결과
    @GetMapping("/result")
    public String result(Model model, @LoginUser SessionUser user, @PageableDefault Pageable pageable){
        model.addAttribute("resultList", userService.findCandidateList(pageable));
        model.addAttribute("sessionUser", user);

        return "result";
    }

    // 학과별 투표결과
    @GetMapping("/voteresult")
    public String departmentResult(@RequestParam(value = "department") Department department, Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        model.addAttribute("department", department);
        return "voteresult";
    }

    // 등록된 후보 리스트
    @GetMapping("/candidatelist")
    public String registedCandidate(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user) {
        model.addAttribute("candidateList", userService.findCandidateList(pageable));
        model.addAttribute("sessionUser", user);
        return "candidatelist";
    }

    // 후보등록
    @GetMapping("/regcandidate")
    public String regCandidate(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "regcandidate";
    }
}
