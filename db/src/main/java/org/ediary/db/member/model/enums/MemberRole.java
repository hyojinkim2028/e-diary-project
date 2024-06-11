package org.ediary.db.member.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum MemberRole {
    MASTER("마스터"),
    ADMIN("관리자"),
    USER("일반유저"),

    ;

    private String description;
}
