package com.soo0.bulletin_board.exception;

import org.springframework.dao.DataAccessException;

/**
 * MyBatis에서 쿼리 실행 결과로 반환되는, 변경된 행의 수가 0인 경우 발생하는 예외 클래스이다.
 */
public class DataModificationException extends DataAccessException implements ErrorCodeSupport {
    /**
     * 에러 코드
     */
    private final ErrorCode errorCode;

    /**
     * DataModificationException 클래스의 생성자이다.
     *
     * @param errorCode 에러 코드
     * @param message 에러 메시지
     */
    public DataModificationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * DataModificationException 클래스의 생성자이다.
     *
     * @param errorCode 에러 코드
     * @param message 에러 메시지
     * @param cause 원인 예외
     */
    public DataModificationException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
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
