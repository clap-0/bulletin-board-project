package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.exception.DuplicateUserException;
import com.soo0.bulletin_board.exception.ErrorCode;
import com.soo0.bulletin_board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자와 관련된 비즈니스 로직을 처리하는 클래스이다.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 중복 아이디가 있는지 확인하고, 비밀번호를 암호화한 후 사용자를 등록하는 메서드이다.
     *
     * @param user 등록할 사용자의 정보를 담은 User 객체
     */
    @Override
    @Transactional
    public void signup(User user) {
        validateDuplicateUser(user);

        encodePassword(user);

        userMapper.insert(user);
    }

    /**
     * 사용자의 비밀번호를 암호화하는 메서드이다.
     *
     * @param user 암호화할 사용자 정보를 담은 User 객체
     */
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    /**
     * 중복되는 이메일을 가진 사용자가 데이터베이스에 있는지 확인하는 메서드이다.
     *
     * @param user 중복 확인을 할 사용자 정보를 담은 User 객체
     * @throws DuplicateUserException 중복되는 이메일을 가진 사용자가 있는 경우 발생하는 예외
     */
    private void validateDuplicateUser(User user) {
        String email = user.getEmail();
        User findUser = userMapper.selectByEmail(email);

        if (findUser != null) {
            throw new DuplicateUserException(ErrorCode.DUPLICATE_LOGIN_ID, "Duplicate user email");
        }
    }

    /**
     * 로그인 요청을 처리하는 메서드이다.
     *
     * @param loginRequest 로그인 요청 정보를 담은 LoginRequest 객체
     * @return 사용자의 ID. 로그인 성공시에만 반환
     */
    @Transactional
    @Override
    public Integer login(LoginRequest loginRequest) {
        checkAccount(loginRequest.getEmail(), loginRequest.getPassword());

        User user = userMapper.selectByEmail(loginRequest.getEmail());

        return user.getUserId();
    }

    /**
     * 주어지는 이메일과 비밀번호를 가진 계정이 존재하는지 확인하는 메서드이다.
     *
     * @param email 로그인 요청 이메일
     * @param password 로그인 요청 비밀번호
     * @throws BadCredentialsException 주어진 이메일을 가지는 계정이 없거나 비밀번호가 일치하지 않으면 발생하는 예외
     */
    private void checkAccount(String email, String password) {
        boolean hasAccount = false;
        User user = userMapper.selectByEmail(email);

        if (user != null) {
            hasAccount = passwordEncoder.matches(password, user.getPassword());
        }

        if (!hasAccount) {
            throw new BadCredentialsException("Incorrect username or password.");
        }
    }
}
