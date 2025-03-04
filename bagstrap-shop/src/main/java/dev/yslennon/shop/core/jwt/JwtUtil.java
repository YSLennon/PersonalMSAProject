package dev.yslennon.shop.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${spring.jwt.secret}")
    private String secret;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Claims jwtClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey).
                build().
                parseSignedClaims(token)
                .getPayload();
    }

    public String getEmail(String token) {
        return jwtClaims(token)
                .get("email", String.class);
    }

    public String getName(String token) {
        return jwtClaims(token)
                .get("name", String.class);
    }

    public List<String> getRoles(String token) {
        Claims claims = jwtClaims(token);
        List<?> roles = claims.get("roles", List.class);

        if (roles == null) return Collections.emptyList();

        return roles.stream()
                .filter(role -> role instanceof String)
                .map(role -> (String) role)
                .toList();
    }
}