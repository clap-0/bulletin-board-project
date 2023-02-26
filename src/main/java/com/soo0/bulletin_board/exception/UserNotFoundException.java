package com.soo0.bulletin_board.exception;

/**
 * 존재하지 않는 사용자를 조회, 수정 혹은 삭제하려는 경우 발생하는 예외 클래스이다.
 */
public class UserNotFoundException extends RuntimeException implements ErrorCodeSupport {
    /**
     * 에러 코드
     */
    private final ErrorCode errorCode;

    /**
     * UserNotFoundException 예외 클래스의 생성자이다.
     *
     * @param errorCode 에러 코드
     * @param message 에러 메시지
     */
    public UserNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 에러 코드를 반환하는 메서드이다.
     * @return 에러 코드 객체
     */
    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
