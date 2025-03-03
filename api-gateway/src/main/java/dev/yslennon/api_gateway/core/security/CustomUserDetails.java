package dev.yslennon.api_gateway.core.security;

import dev.yslennon.api_gateway.user.dto.LoginResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final LoginResDto loginResDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return loginResDto.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .toList();
    }

    @Override
    public String getPassword() {
        return loginResDto.getPassword();
    }

    @Override
    public String getUsername() {
        return loginResDto.getEmail();
    }

    public String getNickname() {
        return loginResDto.getNickname();
    }


}
