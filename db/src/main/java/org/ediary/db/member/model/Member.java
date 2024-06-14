package org.ediary.db.member.model;

import jakarta.persistence.*;
import lombok.*;
import org.ediary.db.member.model.enums.MemberRole;
import org.ediary.db.member.model.enums.MemberStatus;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

}


