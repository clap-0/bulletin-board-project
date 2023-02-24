package com.soo0.bulletin_board.exception;

public class DuplicateUserException extends RuntimeException implements ErrorCodeSupport {
    private final ErrorCode errorCode;

    public DuplicateUserException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
