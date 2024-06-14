package org.ediary.api.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.error.AuthErrorCode;
import org.ediary.api.common.error.UserErrorCode;
import org.ediary.api.domain.auth.JwtTokenProvider;
import org.ediary.api.domain.auth.model.JwtToken;
import org.ediary.api.exception.ApiException;
import org.ediary.db.member.MemberRepository;
import org.ediary.db.member.model.Member;
import org.ediary.db.member.model.enums.MemberStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // bean 으로 등록했기 때문에 인터페이스 형식으로 사용 가능
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 회원가입
    public Member register(
            Member member
    ){
        member.setStatus(MemberStatus.REGISTERED);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRegisteredAt(LocalDateTime.now());

        return memberRepository.save(member);
    }

    // 로그인
    public JwtToken signIn(String username, String password) {

        var isExist= memberRepository.findByEmail(username).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
        boolean isValidPassword = passwordEncoder.matches(password,isExist.getPassword());

        if (isValidPassword) {
            // 1. username + password 를 기반으로 Authentication 객체 생성
            // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, isExist.getPassword());
            // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
            // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            JwtToken jwtToken = jwtTokenProvider.generateToken(authentication, isExist.getId());
            return jwtToken;
        } else {
            throw new ApiException(AuthErrorCode.PASSWORD_MISMATCH);
        }

    }


    // 이메일로 등록된 유저 조회
    public Optional<Member> getRegisterUser(String email){
        return memberRepository.findByEmail(email);
    }

}
