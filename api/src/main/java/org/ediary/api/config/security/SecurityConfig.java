package org.ediary.api.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.ediary.api.domain.auth.JwtTokenProvider;
//import org.ediary.api.domain.member.service.CustomUserDetailsService;
//import org.ediary.api.filter.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig{
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    //
//    private final CustomUserDetailsService customUserDetailsService;
//    private final ApplicationConfig applicationConfig;
//
//
//
//    //
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                // REST API 이므로 basic auth 및 csrf 보안을 사용하지 않음
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                // JWT를 사용하기 때문에 세션을 사용하지 않음
//                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(
//                        registry -> registry.requestMatchers("/", "open-api/**","/swagger-ui.html","/swagger-ui/**", "v3/api-docs/**")
//                                .permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
//    }

////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
////    }
//}

import lombok.RequiredArgsConstructor;
import org.ediary.api.domain.auth.JwtTokenProvider;
import org.ediary.api.filter.JwtAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 기능을 비활성화
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(it -> { it
                        // 허용하고 싶은 요청 있으면 permitAll 하여 체인으로 등록해주면 됨
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resource에 대해서는 모든 요청 허용
                        .requestMatchers(SWAGGER.toArray(new String[0])).permitAll() // swagger는 인증 없이 통과
                        .requestMatchers("/open-api/**").permitAll() // open-api 인증 없이 통과
                        // 그 외의 모든 요청에는 인증을 받겠다는 의미
                        .anyRequest().authenticated()
                ;
                })
        // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        ;
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // hash 로 암호화 - 디코딩은 불가
        return new BCryptPasswordEncoder();
    }
}
