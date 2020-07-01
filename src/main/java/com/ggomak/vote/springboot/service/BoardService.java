package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import com.ggomak.vote.springboot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable, BoardType boardType) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return boardRepository.findAllByBoardType(pageable, boardType);
    }

}
