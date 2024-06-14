package org.ediary.api.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ediary.api.common.api.Api;
import org.ediary.api.domain.diary.controller.model.DiaryCreateRequest;
import org.ediary.api.domain.member.business.MemberBusiness;
import org.ediary.api.domain.auth.model.JwtToken;
import org.ediary.api.domain.member.controller.model.MemberResponse;
import org.ediary.api.domain.member.controller.model.MemberSignupRequest;
import org.ediary.db.member.model.dto.SignInDto;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("open-api/members")
public class MemberOpenApiController {
    private final MemberBusiness memberBusiness;


    @PostMapping("/sign-up")
    public Api<MemberResponse> register(
            @Valid
            @RequestBody MemberSignupRequest request) {
        MemberResponse response = memberBusiness.register(request);
        return Api.OK(response);
    }


    @PostMapping("/sign-in")
    public JwtToken signIn(@Valid @RequestBody SignInDto signInDto ) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken response = memberBusiness.signIn(username, password);
        return response;
    }


}
