package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.repository.BoardRepository;
import com.ggomak.vote.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Page<Board> findBoardList(Pageable pageable, BoardType boardType) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return boardRepository.findAllByBoardType(pageable, boardType);
    }

    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board());
    }

    // 게시글 저장
    @Transactional
    public Long save(Board board, SessionUser user) {

        Optional<User> user1 = userRepository.findByStudentId(user.getStudentId());

        board.setBoard(LocalDateTime.now(), LocalDateTime.now(), user1.get());
        return boardRepository.save(board).getIdx();
    }

    // 게시글 수정
    @Transactional
    public Long update(Long idx, Board requestDto) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getBoardType());

        return idx;
    }

    // 게시글 삭제
    @Transactional
    public void delete (Long idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        boardRepository.delete(board);
    }

}
