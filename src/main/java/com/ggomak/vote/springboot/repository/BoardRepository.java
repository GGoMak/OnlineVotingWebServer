package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.Board;
import com.ggomak.vote.springboot.domain.enums.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByBoardType(Pageable pageable, BoardType boardType);
}
