package dev.yslennon.api_gateway.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
