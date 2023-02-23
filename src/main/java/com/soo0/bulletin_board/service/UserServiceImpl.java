package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.domain.vo.UserInfo;
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

    // 회원가입
    @Override
    @Transactional
    public void signup(User user) {
        encodePassword(user);
        userMapper.insertUser(user);
    }

    // 사용자의 비밀번호 암호화
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    // 사용자 중복 확인
    @Override
    public boolean validateDuplicateUser(String email) {
        // 하나의 이메일로는 하나의 계정만 가능
        User findUser = userMapper.selectUserByEmail(email);
        return findUser != null;
    }
}
