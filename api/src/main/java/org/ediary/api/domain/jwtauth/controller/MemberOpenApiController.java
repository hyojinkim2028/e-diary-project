package org.ediary.api.domain.jwtauth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ediary.api.common.api.Api;
import org.ediary.api.domain.jwtauth.service.MemberServiceImpl;
import org.ediary.api.domain.jwtauth.model.JwtToken;
import org.ediary.db.member.model.dto.MemberDto;
import org.ediary.db.member.model.dto.SignInDto;
import org.ediary.db.member.model.dto.SignUpDto;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("open-api/members")
public class MemberOpenApiController {
    private final MemberServiceImpl memberService;

    @PostMapping("/sign-in")
    public JwtToken signIn(@Valid @RequestBody SignInDto signInDto ) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }


    @PostMapping("/sign-up")
    public Api<MemberDto> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        MemberDto savedMemberDto = memberService.signUp(signUpDto);
        return Api.OK(savedMemberDto);
    }

}
