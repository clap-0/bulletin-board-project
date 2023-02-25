package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.exception.DuplicateUserException;
import com.soo0.bulletin_board.exception.ErrorCode;
import com.soo0.bulletin_board.exception.UserNotFoundException;
import com.soo0.bulletin_board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 기능
    @Override
    @Transactional
    public void signup(User user) {
        validateDuplicateUser(user);
        encodePassword(user);
        userMapper.insertUser(user);
    }

    // 사용자의 비밀번호 암호화
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    // 사용자 중복 확인
    private void validateDuplicateUser(User user) {
        String email = user.getEmail();
        // 하나의 이메일로는 하나의 계정만 가능
        User findUser = userMapper.selectUserByEmail(email);
        if (findUser != null) {
            throw new DuplicateUserException(ErrorCode.DUPLICATE_LOGIN_ID, "Duplicate user email");
        }
    }

    // 로그인 기능
    @Transactional
    @Override
    public Integer login(LoginRequest loginRequest) {
        User user = userMapper.selectUserByEmail(loginRequest.getEmail());
        // 주어진 이메일을 가지는 계정이 없거나 비밀번호가 일치하지 않으면 UserNotFoundException 던짐
        if (!validateLoginRequest(loginRequest, user)) {
            throw new UserNotFoundException(ErrorCode.UNAUTHORIZED, "Incorrect username or password.");
        }
        // 이메일과 비밀번호가 DB에 있는 정보와 일치하면 해당 사용자의 ID 반환
        return user.getUserId();
    }

    // 로그인 요청으로 주어지는 계정 정보에 일치하는 계정이 있는지 확인
    private boolean validateLoginRequest(LoginRequest loginRequest, User user) {
        // 계정이 존재하는지 확인
        if (user == null) {
            return false;
        }
        // 로그인 요청으로 입력된 비밀번호와 계정의 비밀번호가 일치하는지 확인
        return passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
    }

}
