package com.example.demo.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    private static final String SECRET = "ayo1990aayo1990aayo1990aayo1990a";

    private static final long EXPIRE = 60 * 24 * 7;

    public static final String HEADER = "Authorization";

    /**
     * 生成jwt token
     */
    public String generateToken(String username) {
        SecretKey signingKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        //过期时间
        LocalDateTime tokenExpirationTime = LocalDateTime.now().plusMinutes(EXPIRE);
        return "Bearer " + Jwts.builder()
                .signWith(signingKey, Jwts.SIG.HS256)
                .header().add("typ", "JWT").and()
                .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .subject(username)
                .expiration(Timestamp.valueOf(tokenExpirationTime))
                .claims(Map.of("username", username))
                .compact();
    }

    public Claims getClaimsByToken(String token) {
        SecretKey signingKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 检查token是否过期
     * @param expiration
     * @return
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
