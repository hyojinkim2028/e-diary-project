package org.ediary.api.domain.member.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ediary.api.domain.member.converter.MemberConverter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberConverter memberConverter;

    @GetMapping("/me")
    public Object me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails userSession
            ) {
//        return memberConverter.toResponse(userSession);
        return userSession;
    }

}
