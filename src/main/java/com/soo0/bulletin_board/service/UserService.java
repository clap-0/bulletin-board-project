package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    // 회원가입
    @Transactional
    void signup(User user);
}
