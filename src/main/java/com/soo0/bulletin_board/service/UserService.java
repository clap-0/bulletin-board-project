package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.vo.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자와 관련된 비즈니스 로직을 처리하는 인터페이스이다.
 */
public interface UserService {
    /**
     * 중복 아이디가 있는지 확인하고, 비밀번호를 암호화한 후 사용자를 등록하는 메서드이다.
     *
     * @param user 등록할 사용자의 정보를 담은 User 객체
     */
    @Transactional
    void signup(User user);

    /**
     * 로그인 요청을 처리하는 메서드이다.
     *
     * @param loginRequest 로그인 요청 정보를 담은 LoginRequest 객체
     * @return 사용자의 ID. 로그인 성공시에만 반환
     */
    @Transactional
    Integer login(LoginRequest loginRequest);
}
