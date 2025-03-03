package dev.yslennon.api_gateway.user.controller;

import dev.yslennon.api_gateway.core.dto.ApiResponse;
import dev.yslennon.api_gateway.user.dto.JoinReqDto;
import dev.yslennon.api_gateway.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<String>> join(@RequestBody JoinReqDto joinReqDto) {
        return userService.insertUser(joinReqDto);
    }
}
