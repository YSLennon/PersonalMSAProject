package dev.yslennon.api_gateway.user.service;

import dev.yslennon.api_gateway.core.dto.ApiResponse;
import dev.yslennon.api_gateway.user.dto.JoinReqDto;
import dev.yslennon.api_gateway.user.entity.User;
import dev.yslennon.api_gateway.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<ApiResponse<String>> insertUser(JoinReqDto joinReqDto) {
        try {
            User user = User.builder()
                    .email(joinReqDto.getEmail())
                    .password(passwordEncoder.encode(joinReqDto.getPassword()))
                    .nickname(joinReqDto.getNickname())
                    .roles(joinReqDto.getRoles())
                    .build();
            userRepository.save(user);
            //TODO location 경로 추가해야함 /user/[id] 유저 정보 페이지
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ApiResponse.created(location, "회원가입이 완료되었습니다.", null);
        } catch (Exception e) {
            return ApiResponse.error("회원가입 요청이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
