package com.soo0.bulletin_board.domain.vo;

import java.util.Date;

/**
 * 사용자 정보를 나타내는 인터페이스이다.
 */
public interface User {
    /**
     * 사용자 ID를 반환하는 메서드이다.
     * @return 사용자 ID
     */
    Integer getUserId();

    /**
     * 사용자 이메일 주소를 반환하는 메서드이다.
     * @return 사용자 이메일
     */
    String getEmail();

    /**
     * 사용자 비밀번호를 반환하는 메서드이다.
     * @return 사용자 비밀번호
     */
    String getPassword();

    /**
     * 사용자 비밀번호를 설정하는 메서드이다.
     * @param password 설정할 비밀번호
     */
    void setPassword(String password);

    /**
     * 사용자 이름을 반환하는 메서드이다.
     * @return 사용자 이름
     */
    String getUserName();

    /**
     * 사용자의 가입일을 반환하는 메서드이다.
     * @return 사용자 가입일
     */
    Date getRegisterDate();

    /**
     * 사용자가 관리자 권한을 가지고 있는지 반환하는 메서드이다.
     * @return 관리자 여부
     */
    Boolean getIsAdmin();
}
