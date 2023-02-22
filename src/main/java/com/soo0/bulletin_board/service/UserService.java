package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    // 회원가입
    @Transactional
    void signup(UserDto userDto);

    boolean validateDuplicateUser(String email);
}
