package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Pledge;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.repository.PledgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final PledgeRepository pledgeRepository;

    public Pledge findPledge(String department){
        Pledge pledge = pledgeRepository.findByDepartment(Department.computerScience);

        return pledge;
    }
}
