package org.ediary.api.domain.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String nickname;

    private String email;

    private LocalDateTime registeredAt;

    private LocalDateTime lastLoginAt;
}
