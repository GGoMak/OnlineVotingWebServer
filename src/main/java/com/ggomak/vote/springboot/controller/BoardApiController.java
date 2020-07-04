package com.ggomak.vote.springboot.controller;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.oauthsecurity.annotation.LoginUser;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody Board board, @LoginUser SessionUser user) {
        return boardService.save(board, user);
    }

    @PutMapping("/api/v1/posts/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody Board requestDto) {
        return boardService.update(idx, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{idx}")
    public Long delete(@PathVariable Long idx) {
        boardService.delete(idx);
        return idx;
    }
}
