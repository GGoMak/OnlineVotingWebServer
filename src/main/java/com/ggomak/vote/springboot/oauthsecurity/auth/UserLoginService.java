package com.ggomak.vote.springboot.oauthsecurity.auth.dto;

import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserLoginService implements UserDetailsService {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByStudentId(studentId);

        if (!user.isPresent()) {    // 유저정보가 없을 시(회원 가입이 안되어 있을 시) Exception 발생
            throw new UsernameNotFoundException("FAIL");
        }

        UserAttributes originUser = new UserAttributes();
        originUser.setUsername(user.get().getName());
        originUser.setPassword(user.get().getPassword());
        originUser.setAuthorities(getAuthorities(studentId));
        originUser.setEnabled(true);
        originUser.setAccountNonExpired(true);
        originUser.setAccountNonLocked(true);
        originUser.setCredentialsNonExpired(true);

        httpSession.setAttribute("user", new SessionUser(user.get())); // 세션 등록

        return originUser;
    }

    /* 추후 권한 부여 방식 수정 */
    public Collection<GrantedAuthority> getAuthorities(String studentId) {
        Optional<User> user = userRepository.findByStudentId(studentId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRoleType().getKey()));
        return authorities;
    }
}
