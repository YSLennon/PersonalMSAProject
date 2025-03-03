package dev.yslennon.api_gateway.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private T payload;

    public static <T> ResponseEntity<ApiResponse<T>> created(URI location, T payload) {
        return ResponseEntity.created(location).body(new ApiResponse<>("created", "리소스가 추가되었습니다.", payload));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(URI location, String message, T payload) {
        return ResponseEntity.created(location).body(new ApiResponse<>("created", message, payload));
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T payload) {
        return ResponseEntity.ok(new ApiResponse<>("success", "요청이 성공했습니다.", payload));
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T payload) {
        return ResponseEntity.ok(new ApiResponse<>("success", message, payload));
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ApiResponse<>("error", message, null));
    }

}
