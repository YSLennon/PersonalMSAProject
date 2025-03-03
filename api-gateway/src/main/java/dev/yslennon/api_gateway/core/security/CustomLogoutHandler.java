package dev.yslennon.api_gateway.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yslennon.api_gateway.core.dto.ApiResponse;
import dev.yslennon.api_gateway.core.security.jwt.JwtUtil;
import dev.yslennon.api_gateway.core.security.jwt.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final JwtUtil jwtUtil;
    private final RefreshService refreshService;
    private final ObjectMapper objectMapper;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            refreshService.deleteRefreshForLogout(request);

            String jsonResponse = objectMapper.writeValueAsString(new ApiResponse<>(
                    "success",
                    "로그아웃되었습니다.",
                    null
            ));
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
