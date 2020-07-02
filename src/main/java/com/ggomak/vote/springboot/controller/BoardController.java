package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/notice")
    public String notice(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.notice));
        return "notice";
    }

    @GetMapping("/free")
    public String free(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.free));
        return "free";
    }

    @GetMapping("/board")
    public String post(){
        return "boardform";
    }
}
