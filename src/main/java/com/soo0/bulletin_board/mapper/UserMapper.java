package com.soo0.bulletin_board.mapper;

import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.domain.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    int insertUser(User user); // 회원정보 등록
    User selectUser(int userId);  // 회원정보 조회
    User selectUserByEmail(String email);  // 이메일로 회원정보 조회
    int countUser();    // 회원 수 세기
    int updateUser(User user);    // 회원정보 수정
    int updatePassword(Map map);    // 비밀번호 변경
    int deleteUser(int userId); // 회원정보 삭제
}
