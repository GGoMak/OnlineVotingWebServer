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

    // 게시글 저장
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody Board board, @LoginUser SessionUser user) {
        return boardService.save(board, user);
    }

    // 게시글 수정
    @PutMapping("/api/v1/posts/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody Board requestDto) {
        return boardService.update(idx, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/v1/posts/{idx}")
    public Long delete(@PathVariable Long idx) {
        boardService.delete(idx);
        return idx;
    }
}
