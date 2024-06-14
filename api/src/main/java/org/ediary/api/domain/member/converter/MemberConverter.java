package org.ediary.api.domain.member.converter;


import lombok.RequiredArgsConstructor;
import org.ediary.api.common.annotation.Converter;
import org.ediary.api.domain.member.controller.model.MemberResponse;
import org.ediary.api.domain.member.controller.model.MemberSignupRequest;
import org.ediary.db.member.model.Member;

@RequiredArgsConstructor
@Converter
public class MemberConverter {

    public Member toEntity(
            MemberSignupRequest request
    ){
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .role(request.getRole())
                .build();

    }

    public MemberResponse toResponse(
            Member member
    ){
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .status(member.getStatus())
                .role(member.getRole())
                .registeredAt(member.getRegisteredAt())
                .unregisteredAt(member.getUnregisteredAt())
                .lastLoginAt(member.getLastLoginAt())
                .build();

    }

//    public MemberResponse toResponse(UserSession userSession){
//        return MemberResponse.builder()
//                .id(userSession.getId())
//                .email(userSession.getEmail())
//                .role(userSession.getRole())
//                .build();
//    }
}
