package dev.yslennon.api_gateway.core.security.jwt;

import dev.yslennon.api_gateway.core.dto.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtUtil jwtUtil;

    public ResponseEntity<ApiResponse<String>> validateRefreshToken(HttpServletRequest request) {
        String ClientRefreshToken = getRefreshTokenFromCookie(request);
        String RedisRefreshToken = findRefreshToken(jwtUtil.getEmail(ClientRefreshToken));

        if (jwtUtil.isExpired(RedisRefreshToken) || !RedisRefreshToken.equals(ClientRefreshToken)) {
            return ApiResponse.error("Token is not valid", HttpStatus.UNAUTHORIZED);
        } else {
            String newAccessToken = jwtUtil.createAccessToken(
                    jwtUtil.getEmail(ClientRefreshToken),
                    jwtUtil.getName(ClientRefreshToken),
                    jwtUtil.getRoles(ClientRefreshToken)
            );
            return ApiResponse.success(newAccessToken);
        }
    }

    public void storeRefreshToken(String email, String token) {
        redisTemplate.opsForValue().set(getRefreshKey(email), token, Duration.ofDays(14));
    }

    public void deleteRefreshForLogout(HttpServletRequest request) {
        String token = getRefreshTokenFromCookie(request);
        deleteRefreshToken(jwtUtil.getEmail(token));
    }

    private String getRefreshKey(String email) {
        return "refresh: " + email;
    }

    private void deleteRefreshToken(String email) {
        redisTemplate.delete(getRefreshKey(email));
    }

    private String findRefreshToken(String email) {
        Object token = redisTemplate.opsForValue().get(getRefreshKey(email));
        return (token != null) ? token.toString() : null;
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        try {
            final Cookie[] cookies = request.getCookies();

            String token = Arrays.stream(cookies)
                    .filter(cookie -> "Authorization".equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);

            return token;
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

}
