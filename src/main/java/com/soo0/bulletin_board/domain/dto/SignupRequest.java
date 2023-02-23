package com.soo0.bulletin_board.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class SignupRequest {
    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,6}$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    // FIXME - 정규식에서 비밀번호 자릿수 범위를 매개변수를 통해 받도록 변경
    @Pattern(regexp = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함한 8~16자입니다.")
    private String password;

    private String passwordConfirmation;

    @Size(min = 2, max = 30, message = "이름은 {min}~{max}자입니다.")
    private String userName;
}
