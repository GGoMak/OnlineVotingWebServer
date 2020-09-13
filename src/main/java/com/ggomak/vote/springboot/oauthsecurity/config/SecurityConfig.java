package com.ggomak.vote.springboot.oauthsecurity.config;

import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoginService userLoginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**/**", "/sass/**", "/webfonts/**","/js/**", "/h2-console/**", "/profile", "/notice", "/free", "/voteinfo", "/api/v2/**", "/api/v3/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/v2/login")
                .usernameParameter("studentId")
                .passwordParameter("phoneNumber")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  // CustomLogin(자체 로그인) 설정
        auth.userDetailsService(userLoginService);
    }
}
