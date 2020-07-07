package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.Pledge;
import com.ggomak.vote.springboot.domain.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PledgeRepository extends JpaRepository<Pledge, Long> {

    Pledge findByDepartment(Department department);
}
