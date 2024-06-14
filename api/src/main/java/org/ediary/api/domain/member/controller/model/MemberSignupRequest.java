package org.ediary.api.domain.member.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ediary.db.member.model.enums.MemberRole;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberSignupRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotNull
    private MemberRole role;
}
