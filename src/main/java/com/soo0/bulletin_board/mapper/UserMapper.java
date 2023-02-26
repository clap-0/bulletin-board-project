package com.soo0.bulletin_board.mapper;

import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.domain.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 사용자 관련 기능을 담당하는 Mapper 인터페이스이다.
 */
@Mapper
public interface UserMapper {
    /**
     * 사용자 정보를 등록하는 메서드이다.
     *
     * @param user 등록할 사용자 정보를 담은 User 객체
     * @return 추가된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int insert(User user);

    /**
     * 사용자 정보를 조회하는 메서드이다.
     *
     * @param userId 조회할 사용자의 ID
     * @return 사용자 정보를 담은 UserInfo 객체
     */
    UserInfo select(Integer userId);

    /**
     * 이메일을 통해 사용자 정보를 조회하는 메서드이다.
     *
     * @param email 조회할 사용자의 이메일 주소
     * @return 사용자 정보를 담은 UserInfo 객체
     */
    UserInfo selectByEmail(String email);

    /**
     * 사용자의 수를 반환하는 메서드이다.
     *
     * @return 데이터베이스에 저장된 사용자의 수
     */
    int count();

    /**
     * 사용자 정보를 수정하는 메서드이다.
     *
     * @param user 수정할 사용자 정보를 담은 User 객체
     * @return 변경된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int update(User user);

    /**
     * 사용자 비밀번호를 변경하는 메서드이다.
     *
     * @param userId 비밀번호를 변경할 사용자의 ID
     * @param password 변경할 비밀번호
     * @return 변경된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int updatePassword(@Param("userId") Integer userId, @Param("password") String password);

    /**
     * 사용자 정보를 삭제하는 메서드이다.
     *
     * @param userId 삭제할 사용자의 ID
     * @return 삭제된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int delete(Integer userId);
}
