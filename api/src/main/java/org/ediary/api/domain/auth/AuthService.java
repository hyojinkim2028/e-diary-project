package org.ediary.api.domain.auth;

import lombok.RequiredArgsConstructor;
import org.ediary.api.domain.member.service.MemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class AuthService implements UserDetailsService {

        private final MemberService memberService;
        private final PasswordEncoder passwordEncoder;

        // 로그인 인증 처리
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            var memberEntity = memberService.getRegisterUser(username);

            return memberEntity.map(it -> {
                        var userSession = UserSession.builder()
                                .id(it.getId())
                                .email(it.getEmail())
                                .password(passwordEncoder.encode(it.getPassword()))
                                .role(it.getRole())
                                .status(it.getStatus())
                                .build();

                        return userSession;
                    })
                    .orElseThrow(() -> new UsernameNotFoundException(username));
        }
    }
