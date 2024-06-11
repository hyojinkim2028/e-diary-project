package org.ediary.api.domain.jwtauth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ediary.api.common.error.AuthErrorCode;
import org.ediary.api.common.error.UserErrorCode;
import org.ediary.api.domain.jwtauth.JwtTokenProvider;
import org.ediary.api.domain.jwtauth.model.JwtToken;
import org.ediary.api.exception.ApiException;
import org.ediary.db.member.model.*;
import org.ediary.db.member.MemberRepository;
import org.ediary.db.member.model.dto.MemberDto;
import org.ediary.db.member.model.dto.SignUpDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtToken signIn(String username, String password) {

        Member isExist = memberRepository.findByUsername(username).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
        boolean isValidPassword = passwordEncoder.matches(password,isExist.getPassword());

        if (isValidPassword) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, isExist.getPassword());

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;

        } else {
            throw new ApiException(AuthErrorCode.PASSWORD_MISMATCH);
        }


    }

    @Transactional
    public MemberDto signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        Member memberEntity = memberRepository.save(signUpDto.toEntity(encodedPassword));
        return MemberDto.toDto(memberEntity);
    }
}
