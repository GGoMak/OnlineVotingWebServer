package com.ggomak.vote.springboot.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    GUEST("ROLE_GUEST", "손님"),
    VOTER("ROLE_VOTER", "투표자"),
    CANDIDATE("ROLE_CANDIDATE", "후보자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
