package com.ggomak.vote.springboot.oauthsecurity.auth.dto;

import com.ggomak.vote.springboot.domain.enums.RoleType;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Getter
public class UserAttributes implements UserDetails {

    private String username;
    private String password;
    private RoleType roleType;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;
}
