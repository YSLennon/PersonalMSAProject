package dev.yslennon.shop.user;

import dev.yslennon.shop.core.entity.BaseEntity;
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

}
