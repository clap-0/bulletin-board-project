package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.UserDto;
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
    public void signup(UserDto userDto) {
        validateDuplicateUser(userDto);
        encodePassword(userDto);
        userMapper.insertUser(userDto);
    }

    private void encodePassword(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
    }

    private void validateDuplicateUser(UserDto userDto) {
        // 하나의 이메일로는 하나의 계정만 가능
        UserDto findUser = userMapper.selectUserByEmail(userDto.getEmail());
        if (findUser != null) {
            String msg = "이미 사용 중입니다. 다른 이메일을 이용해주세요.";
            throw new IllegalStateException(msg);
        }
    }
}
