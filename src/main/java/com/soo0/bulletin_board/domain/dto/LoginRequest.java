package com.soo0.bulletin_board.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 뷰에서 보내는 로그인 요청 정보를 담은 클래스이다.
 */
@Getter @Setter
public class LoginRequest {
    /**
     * 사용자 이메일 주소, 로그인시 아이디로 사용
     */
    @NotNull
    private String email;

    /**
     * 사용자 비밀번호
     */
    @NotNull
    private String password;
}