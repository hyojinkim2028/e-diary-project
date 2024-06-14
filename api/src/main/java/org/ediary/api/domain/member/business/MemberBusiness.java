package org.ediary.api.domain.member.business;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.annotation.Business;
import org.ediary.api.domain.member.converter.MemberConverter;
import org.ediary.api.domain.auth.model.JwtToken;
import org.ediary.api.domain.member.controller.model.MemberResponse;
import org.ediary.api.domain.member.controller.model.MemberSignupRequest;
import org.ediary.api.domain.member.service.MemberService;

@RequiredArgsConstructor
@Business
public class MemberBusiness {
    private final MemberConverter memberConverter;
    private final MemberService memberService;


    public MemberResponse register(
            MemberSignupRequest request
    ){
        memberService.getRegisterUser(request.getEmail());

        var entity = memberConverter.toEntity(request);

        var newEntity = memberService.register(entity);

        var response = memberConverter.toResponse(newEntity);

        return response;
    }

    public JwtToken signIn(String username, String password) {
        return memberService.signIn(username, password);
    }
}
