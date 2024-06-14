//package org.ediary.db.member.model.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.*;
//import org.ediary.db.member.model.Member;
//import org.ediary.db.member.model.enums.MemberRole;
//import org.ediary.db.member.model.enums.MemberStatus;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class SignUpDto {
//
//    @NotBlank
//    private String username;
//
//    @NotBlank
//    private String password;
//
//    @NotBlank
//    private String nickname;
//
//    public Member toEntity(String encodedPassword) {
//
//        return Member.builder()
//                .email(username)
//                .password(encodedPassword)
//                .nickname(nickname)
//                .role(MemberRole.USER)
//                .status(MemberStatus.REGISTERED)
//                .build();
//    }
//}