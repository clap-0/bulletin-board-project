package com.soo0.bulletin_board.exception;

/**
 * 에러 코드를 갖는 사용자 정의 예외 클래스가 구현해야 하는 인터페이스이다.
 */
public interface ErrorCodeSupport {
    /**
     * 에러 코드를 반환하는 메서드이다.
     * @return 에러코드 객체
     */
    ErrorCode getErrorCode();

    /**
     * 에러 메시지를 반환하는 메서드이다.
     * @return 에러 메시지
     */
    String getMessage();
}
