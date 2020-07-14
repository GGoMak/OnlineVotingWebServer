package com.ggomak.vote.springboot.domain.dto;

import com.ggomak.vote.springboot.domain.enums.Department;
import lombok.Getter;

@Getter
public class CandidateRegDto {

    private Department department;
    private String teamName;
    private String candidate1;
    private String candidate2;
    private String candidate3;

    public CandidateRegDto(Department department, String teamName, String candidate1, String candidate2, String candidate3){
        this.department = department;
        this.teamName = teamName;
        this.candidate1 = candidate1;
        this.candidate2 = candidate2;
        this.candidate3 = candidate3;
    }
}
