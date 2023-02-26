package com.soo0.bulletin_board.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 뷰에서 보내는 회원가입 요청 정보를 담은 클래스이다.
 */
@Getter @Setter
@NoArgsConstructor
public class SignupRequest {
    /**
     * 이메일 주소
     */
    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,6}$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    /**
     * 사용자 비밀번호
     * 영문자, 숫자, 특수문자를 포함한 8~16 글자
     */
    @Pattern(regexp = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함한 8~16자입니다.")
    private String password; // FIXME - 정규식에서 비밀번호 자릿수 범위를 매개변수를 통해 받도록 변경

    /**
     * 사용자 비밀번호 확인
     */
    private String passwordConfirmation;

    /**
     * 사용자 이름
     * 2~30 글자로 구성
     */
    @Size(min = 2, max = 30, message = "이름은 {min}~{max}자입니다.")
    private String userName;

    /**
     * 비밀번호와 비밀번호 확인이 일치하는지 확인하는 메서드이다.
     *
     * <pre>
     * Note: @AssertTrue 어노테이션이 실패 시,
     * FieldError 클래스의 field 속성으로 저장되는 이름을 지정하기 위해
     * 함수명을 isPasswordConfirmation으로 지정합니다.
     * 이를 통해 signup.js에서 error의 field와 에러가 발생한 input을 매핑하는 과정을 줄일 수 있습니다.
     * </pre>
     *
     * @return 비밀번호 일치 여부를 나타내는 boolean 값
     */
    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    public boolean isPasswordConfirmation() {
        return password != null && password.equals(passwordConfirmation);
    }
}
