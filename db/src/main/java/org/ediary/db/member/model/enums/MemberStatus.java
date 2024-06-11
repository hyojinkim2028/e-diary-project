package org.ediary.db.member.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
