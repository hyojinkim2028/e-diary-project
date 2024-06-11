package org.ediary.db.member.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ediary.db.member.model.Member;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {

    private Long id;
    private String username;
    private String nickname;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .nickname(nickname)
                .build();
    }
}
