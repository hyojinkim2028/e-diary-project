package org.ediary.api.domain.member.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ediary.db.member.model.enums.MemberRole;
import org.ediary.db.member.model.enums.MemberStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private MemberStatus status;

    private MemberRole role;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}
