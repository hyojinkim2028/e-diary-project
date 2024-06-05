package org.ediary.api.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 기능을 비활성화
                .authorizeHttpRequests(it -> { it
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resource에 대해서는 모든 요청 허용
                        .requestMatchers(SWAGGER.toArray(new String[0])).permitAll() // swagger는 인증 없이 통과
                        .requestMatchers("/open-api/**").permitAll() // open-api 인증 없이 통과
                        .anyRequest().authenticated() // 그 외의 모든 요청에는 인증을 받음
                ;
                })
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 리다이렉트 URL 설정
                        .permitAll() // 로그인 페이지는 모든 사용자에게 접근 허용
                );
        ;
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // hash 로 암호화 - 디코딩 불가
        return new BCryptPasswordEncoder();
    }
}
