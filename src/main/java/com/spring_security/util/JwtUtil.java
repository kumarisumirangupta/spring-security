package com.spring_security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private static final String SECRET =
            "jwt-secret-key-very-long-at-least-32-bytes!!";

    private static final long ACCESS_EXP = 15 * 60 * 1000;     // 15 min
    private static final long REFRESH_EXP = 7 * 24 * 60 * 60 * 1000; // 7 days

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // ================= ACCESS TOKEN =================
    public String generateAccessToken(UserDetails user) {

        List<String> roles = user.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toList();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", roles)
                .claim("type", "ACCESS")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXP))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= REFRESH TOKEN =================
    public String generateRefreshToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("type", "REFRESH")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXP))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= COMMON =================
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validate(String token, UserDetails userDetails) {
        try {
            Claims claims = extractClaims(token);

            return claims.getSubject().equals(userDetails.getUsername())
                    && claims.getExpiration().after(new Date())
                    && "ACCESS".equals(claims.get("type"));

        } catch (Exception e) {
            return false;
        }
    }
}
