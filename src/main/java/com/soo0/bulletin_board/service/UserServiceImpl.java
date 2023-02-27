package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.exception.DataModificationException;
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
    /**
     * 데이터베이스 행 추가/수정/삭제 실패
     */
    private static final int FAIL = 0;
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

        int result = userMapper.insert(user);
        checkResult(result, "Failed to signup");
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
    private void checkAccount(String email, String password) throws BadCredentialsException {
        boolean hasAccount = false;
        User user = userMapper.selectByEmail(email);

        if (user != null) {     // 계정이 존재하는 경우 비밀번호 일치 확인
            hasAccount = passwordEncoder.matches(password, user.getPassword());
        }

        if (!hasAccount) {      // 계정이 존재하지 않거나 비밀번호가 일치하지 않으면 예외 발생
            throw new BadCredentialsException("Incorrect username or password.");
        }
    }

    /**
     * 데이터의 생성/수정/삭제가 이루어졌는지 확인하는 메서드이다.
     *
     * @param result 데이터베이스에서 변경된 행의 수
     * @param message 에러 발생시 출력할 메시지
     * @throws DataModificationException 데이터베이스 행이 변경되지 않았을 때 발생하는 예외
     */
    private void checkResult(int result, String message) throws DataModificationException {
        if (result == FAIL) {
            throw new DataModificationException(ErrorCode.DATA_MODIFICATION_ERROR, message);
        }
    }
}
