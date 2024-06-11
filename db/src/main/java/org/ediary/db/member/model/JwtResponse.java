package org.ediary.db.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String accessToken;

    private String refreshToken;

    @AllArgsConstructor
    public enum UserStatus {
        REGISTERED("등록"),
        UNREGISTERED("해지")
        ;

        private final String description;
    }
}
