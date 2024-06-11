package org.ediary.api.domain.jwtauth.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.ediary.api.domain.jwtauth.service.MemberServiceImpl;
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
    private final MemberServiceImpl memberService;

    @GetMapping("/test")
    public Object test(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails member
            ) {
        return member;
    }

}
