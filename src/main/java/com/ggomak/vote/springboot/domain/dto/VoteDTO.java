package com.ggomak.vote.springboot.domain.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class VoteDTO implements Serializable{

    private Long idx;
    private boolean opposite;

    public VoteDTO(Long idx, boolean opposite){
        this.idx = idx;
        this.opposite = opposite;
    }
}
