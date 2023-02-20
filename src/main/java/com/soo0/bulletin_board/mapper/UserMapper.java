package com.soo0.bulletin_board.mapper;

import com.soo0.bulletin_board.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    int insertUser(UserDto userDto); // 회원정보 등록
    UserDto selectUser(int userId);  // 회원정보 조회
    UserDto selectUserByEmail(String email);  // 이메일로 회원정보 조회
    int countUser();    // 회원 수 세기
    int updateUser(UserDto userDto);    // 회원정보 수정
    int updatePassword(Map map);    // 비밀번호 변경
    int deleteUser(int userId); // 회원정보 삭제
}
