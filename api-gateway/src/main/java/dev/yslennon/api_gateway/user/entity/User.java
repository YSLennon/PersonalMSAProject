package dev.yslennon.api_gateway.user.entity;

import dev.yslennon.api_gateway.core.entity.BaseEntity;
import dev.yslennon.api_gateway.user.dto.LoginResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "TBL_USER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true)
    private String password;
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TBL_USER_ROLE", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> roles = List.of("USER");

    public LoginResDto toLoginResDto() {
        return new LoginResDto(email, nickname, password, roles);
    }

}
