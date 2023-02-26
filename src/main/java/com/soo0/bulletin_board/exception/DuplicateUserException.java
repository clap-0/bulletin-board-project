package com.soo0.bulletin_board.exception;

/**
 * 회원가입 도중 이미 존재하는 이메일을 사용하려는 경우 발생하는 예외 클래스이다.
 */
public class DuplicateUserException extends RuntimeException implements ErrorCodeSupport {
    /**
     * 에러 코드
     */
    private final ErrorCode errorCode;

    /**
     * DuplicateUserException 예외 클래스의 생성자이다.
     *
     * @param errorCode 에러 코드
     * @param message 에러 메시지
     */
    public DuplicateUserException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 에러 코드를 반환하는 메서드이다.
     * @return 에러 코드
     */
    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
