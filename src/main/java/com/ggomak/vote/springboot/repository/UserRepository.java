package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdx(Long idx);

    Optional<User> findByStudentId(String studentId);
}
