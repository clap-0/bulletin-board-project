package com.soo0.bulletin_board.domain.vo;

import lombok.*;

import java.util.Date;

/**
 * 사용자 정보를 나타내는 클래스이다.
 */
@Getter @Setter
@NoArgsConstructor
public class UserInfo implements User {
    /**
     * 사용자 ID
     */
    private Integer userId;

    /**
     * 사용자 이메일 주소
     */
    private String email;

    /**
     * 사용자 비밀번호
     */
    private String password;

    /**
     * 사용자 이름
     */
    private String userName;

    /**
     * 사용자의 가입일
     */
    private Date registerDate;

    /**
     * 관리자 여부
     */
    private Boolean isAdmin;

    /**
     * UserInfo의 생성자이다.
     *
     * @param email 사용자 이메일 주소
     * @param password 사용자 비밀번호
     * @param userName 사용자 이름
     */
    public UserInfo(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
