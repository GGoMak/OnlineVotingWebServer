package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.oauthsecurity.annotation.LoginUser;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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
}
