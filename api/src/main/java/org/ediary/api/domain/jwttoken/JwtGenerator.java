package org.ediary.api.domain.jwttoken;

import org.ediary.db.member.model.JwtResponse;
import org.springframework.stereotype.Component;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;


@Component
public class JwtGenerator {
    private final Key key;
    private final Long accessTokenPlusHour;
    private final Long refreshTokenPlusHour;

    // application.yaml 에서 secret 값 가져와서 key 에 저장
    public JwtGenerator(
            @Value("${jwt.secret.key}") String secretKey,
            @Value("${jwt.access-token.plus-hour}") Long accessTokenPlusHour,
            @Value("${jwt.refresh-token.plus-hour}") Long refreshTokenPlusHour
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenPlusHour = accessTokenPlusHour;
        this.refreshTokenPlusHour = refreshTokenPlusHour;


    }

    // Member 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public JwtResponse generateToken(Long userId) {
        // 권한 가져오기

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + accessTokenPlusHour);

        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
//                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshTokenPlusHour))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
