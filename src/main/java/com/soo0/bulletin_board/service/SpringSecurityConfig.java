package com.soo0.bulletin_board.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 설정 클래스이다.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * HTTP 보안 설정 메서드이다.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception 예외 처리
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().disable()	                	// CORS 방지
                .csrf().disable()	                	// CSRF 방지
                .formLogin().disable()	                // 기본 로그인 페이지 없애기
                .headers().frameOptions().disable();    // X-Frame-Options 비활성화
    }

    /**
     * PasswordEncoder 객체 빈 등록 메서드이다.
     *
     * @return PasswordEncoder 객체
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}