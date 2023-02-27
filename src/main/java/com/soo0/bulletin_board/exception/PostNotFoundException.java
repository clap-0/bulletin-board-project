package com.soo0.bulletin_board.exception;

import java.util.NoSuchElementException;

/**
 * 존재하지 않는 게시글을 조회, 수정 혹은 삭제하려는 경우 발생하는 예외 클래스이다.
 */
public class PostNotFoundException extends NoSuchElementException implements ErrorCodeSupport {
    /**
     * 에러 코드
     */
    private final ErrorCode errorCode;

    /**
     * PostNotFoundException 예외 클래스의 생성자이다.
     *
     * @param errorCode 에러 코드
     * @param message 에러 메시지
     */
    public PostNotFoundException(ErrorCode errorCode, String message) {
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
