package com.ggomak.vote.springboot.domain.dto;

import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserRoleChangeDTO implements Serializable{

    private Long idx;
    private String value;

    public UserRoleChangeDTO(Long idx, String value){
        this.idx = idx;
        this.value = value;
    }
}