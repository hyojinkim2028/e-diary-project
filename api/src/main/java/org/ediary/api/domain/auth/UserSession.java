package org.ediary.api.domain.auth;

import lombok.Builder;
import lombok.Data;
import org.ediary.db.member.model.enums.MemberRole;
import org.ediary.db.member.model.enums.MemberStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


@Data
@Builder
public class UserSession implements UserDetails {

    private Long id;

    private String email;

    private String password;

    private MemberRole role;

    private MemberStatus status;

//    private LocalDateTime registeredAt;
//
//    private LocalDateTime unregisteredAt;
//
//    private LocalDateTime lastLoginAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == MemberStatus.REGISTERED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == MemberStatus.REGISTERED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == MemberStatus.REGISTERED;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
